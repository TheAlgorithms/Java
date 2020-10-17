package org.folio.codex;

import static org.folio.codex.ResultInformation.analyzeResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.ws.rs.core.Response;

import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.client.WebClient;
import org.z3950.zing.cql.CQLNode;
import org.z3950.zing.cql.CQLRelation;
import org.z3950.zing.cql.CQLTermNode;

import org.folio.codex.comparator.InstanceComparator;
import org.folio.codex.exception.GetModulesFailException;
import org.folio.codex.exception.QueryValidationException;
import org.folio.codex.parser.InstanceCollectionParser;
import org.folio.common.OkapiParams;
import org.folio.okapi.common.CqlUtil;
import org.folio.okapi.common.XOkapiHeaders;
import org.folio.rest.annotations.Validate;
import org.folio.rest.jaxrs.model.Instance;
import org.folio.rest.jaxrs.model.InstanceCollection;
import org.folio.rest.jaxrs.model.ResultInfo;
import org.folio.rest.jaxrs.resource.CodexInstances;

@java.lang.SuppressWarnings({"squid:S1192"})
public class Multiplexer implements CodexInstances {

  private static final String TOTAL_RECORDS = "totalRecords";
  private static final String RESULT_INFO = "resultInfo";

  public static class MuxCollection<T> {
    int statusCode;
    Buffer message;
    CollectionExtension<T> colExt;
    String query;
  }

  public static class CollectionExtension<T> {
    private ResultInfo resultInfo;
    private List<T> items;

    public ResultInfo getResultInfo() {
      return resultInfo;
    }

    public void setResultInfo(ResultInfo resultInfo) {
      this.resultInfo = resultInfo;
    }

    public List<T> getItems() {
      return items;
    }

    public void setItems(List<T> items) {
      this.items = items;
    }
  }

  private static Logger logger = LoggerFactory.getLogger("codex.mux");

  private OkapiClient okapiClient = new OkapiClient();

  @SuppressWarnings({"squid:S00107"})
  private <T> void getByQuery(String module, MergeRequest<T> mergeRequest, String query, int offset, int limit,
                              CodexInterfaces codexInterface, Function<String, CollectionExtension<T>> parser,
                              Handler<AsyncResult<Void>> handler) {

    WebClient client = WebClient.create(mergeRequest.getVertxContext().owner());

    String url = mergeRequest.getHeaders().get(XOkapiHeaders.URL) + codexInterface.getQueryPath()
    + "offset=" + offset + "&limit=" + limit;
    try {
      if (query != null) {
        url += "&query=" + URLEncoder.encode(query, "UTF-8");
      }
    } catch (UnsupportedEncodingException ex) {
      handler.handle(Future.failedFuture(ex.getMessage()));
      return;
    }
    logger.info("getByQuery url=" + url);
    okapiClient.<T>getUrl(module, url, client, mergeRequest.getHeaders())
      .onComplete(res -> {
        client.close();
        if (res.failed()) {
          logger.warn("getByQuery. getUrl failed " + res.cause());
          handler.handle(Future.failedFuture(res.cause()));
        } else {
          MuxCollection<T> muxCollection = getMuxCollection(query, parser, handler, res);
          if (muxCollection == null) return;
          mergeRequest.getMuxCollectionMap().put(module, muxCollection);
          handler.handle(Future.succeededFuture());
        }
      });
  }

  private <T> MuxCollection<T> getMuxCollection(String query, Function<String, CollectionExtension<T>> parser,
                                                Handler<AsyncResult<Void>> handler, AsyncResult<MuxCollection<T>> res) {
    MuxCollection<T> muxCollection = res.result();
    if (muxCollection.statusCode == 200) {
      try {
        JsonObject collectionJsonObject = new JsonObject(muxCollection.message.toString());
        if (collectionJsonObject.getJsonObject(RESULT_INFO) == null) {
          JsonObject resultInfo = new JsonObject();
          resultInfo.put(TOTAL_RECORDS, collectionJsonObject.remove(TOTAL_RECORDS));
          resultInfo.put("facets", new JsonArray());
          resultInfo.put("diagnostics", new JsonArray());
          collectionJsonObject.put(RESULT_INFO, resultInfo);
        }
        muxCollection.colExt = parser.apply(collectionJsonObject.encode());
        muxCollection.query = query;
      } catch (Exception e) {
        handler.handle(Future.failedFuture(e));
        return null;
      }
    }
    return muxCollection;
  }

  public <T> Future<CollectionExtension<T>> mergeSort(List<String> modules, CQLParameters<T> cqlParameters,
                                                      MergeRequest<T> mergeRequest, CodexInterfaces codexInterface,
                                                      Function<String, CollectionExtension<T>> parser) {

    List<Future> futures = new LinkedList<>();
    for (String module : modules) {
      final CQLNode cqlNode = cqlParameters.getCqlNode();
      if (cqlNode == null) {
        Promise<Void> promise = Promise.promise();
        getByQuery(module, mergeRequest, null, 0, mergeRequest.getOffset() + mergeRequest.getLimit(), codexInterface, parser, promise);
        futures.add(promise.future());
      } else {
        CQLNode node = filterSource(module, cqlNode);
        if (node != null) {
          Promise<Void> promise = Promise.promise();
          getByQuery(module, mergeRequest, node.toCQL(), 0, mergeRequest.getOffset() + mergeRequest.getLimit(), codexInterface, parser, promise);
          futures.add(promise.future());
        }
      }
    }
    return CompositeFuture.all(futures)
      .map( o ->  mergeSet2(mergeRequest, cqlParameters.getComparator()));
  }

  private <T> CollectionExtension<T> mergeSet2(MergeRequest<T> mergeRequest, Comparator<T> comparator) {

    CollectionExtension<T> collectionExtension = new CollectionExtension<>();
    final Map<String, MuxCollection<T>> muxCollectionMap = mergeRequest.getMuxCollectionMap();
    collectionExtension.setResultInfo(ResultInformation.createResultInfo(muxCollectionMap));
    collectionExtension.setItems(new ArrayList<>());
    int[] pointers = new int[muxCollectionMap.size()]; // all 0
    for (int barrier = 0; barrier < mergeRequest.getOffset() + mergeRequest.getLimit(); barrier++) {
      T minElement = null;
      int minIndex = -1;
      int index = 0;
      for (MuxCollection<T> muxCollection : muxCollectionMap.values()) {
        int pointer = pointers[index];
        if (muxCollection.colExt != null) {
          List<T> collection = muxCollection.colExt.getItems();
          if (pointer < collection.size()) {
            T element = collection.get(pointer);
            if (minElement == null
              || (comparator == null && pointers[minIndex] > pointers[index])
              || (comparator != null && comparator.compare(minElement, element) > 0)) {
              minIndex = index;
              minElement = element;
            }
          }
        }
        index++;
      }
      if (minElement == null) {
        break;
      }
      pointers[minIndex]++;
      if (barrier >= mergeRequest.getOffset()) {
        collectionExtension.getItems().add(minElement);
      }
    }
    return collectionExtension;
  }

  private CQLNode filterSource(String moduleId, CQLNode top) {

    CQLRelation relation = new CQLRelation("=");

    Comparator<CQLTermNode> indexTermComparator = (CQLTermNode n1, CQLTermNode n2) ->
      (n1.getIndex().equals(n2.getIndex()) && !n1.getTerm().equals(n2.getTerm())) ? -1 : 0;

    Comparator<CQLTermNode> indexComparator = (CQLTermNode n1, CQLTermNode n2) -> n1.getIndex().equals(n2.getIndex()) ? 0 : -1;

    CQLTermNode source = null;
    if (moduleId.startsWith("mod-codex-ekb")) {
      source = new CQLTermNode("source", relation, "kb");
    } else if (moduleId.startsWith("mod-codex-inventory")) {
      source = new CQLTermNode("source", relation, "local");
    } else if (moduleId.startsWith("mod-agreements")) {
      source = new CQLTermNode("source", relation, "localkb");
    } else if (moduleId.startsWith("mock")) { // for Unit testing
      source = new CQLTermNode("source", relation, moduleId);
    }
    if (source == null) {
      return top;
    } else {
      if (!CqlUtil.eval(top, source, indexTermComparator)) {
        logger.info("Filter out module " + moduleId);
        return null;
      }
      logger.info("Reducing query for module " + moduleId);
      return CqlUtil.reducer(top, source, indexComparator);
    }
  }

  @Override
  @Validate
  public void getCodexInstances(String query, int offset, int limit, String lang, Map<String, String> okapiHeaders,
                                Handler<AsyncResult<Response>> handler, Context vertxContext) {
    logger.info("Codex.mux getCodexInstances");
    OkapiParams okapiParams;
    try{
      okapiParams = new OkapiParams(okapiHeaders);
    }
    catch (IllegalArgumentException ex){
      handler.handle(
        Future.succeededFuture(CodexInstances.GetCodexInstancesResponse.respond400WithTextPlain("Validation of okapi headers failed: " + ex.getMessage())));
      return;
    }

    okapiClient.getModuleList(vertxContext, okapiParams, CodexInterfaces.CODEX)
      .compose(moduleList -> getInstanceCollectionExtension(query, offset, limit, okapiHeaders, vertxContext, moduleList))
      .map(instanceCollectionExtension -> {
        handler.handle(Future.succeededFuture(GetCodexInstancesResponse.respond200WithApplicationJson(
          new InstanceCollection()
            .withInstances(instanceCollectionExtension != null ? instanceCollectionExtension.getItems() : null)
            .withResultInfo(instanceCollectionExtension != null ? instanceCollectionExtension.getResultInfo() : null))));
          return null;
      })
      .otherwise(throwable -> {
        if (throwable instanceof GetModulesFailException){
          handler.handle(Future.succeededFuture(
            CodexInstances.GetCodexInstancesResponse.respond401WithTextPlain(throwable.getMessage())));
        } else if (throwable instanceof QueryValidationException || throwable instanceof IllegalArgumentException){
          handler.handle(
            Future.succeededFuture(CodexInstances.GetCodexInstancesResponse.respond400WithTextPlain(throwable.getMessage())));
        } else {
          handler.handle(
            Future.succeededFuture(CodexInstances.GetCodexInstancesResponse.respond500WithTextPlain(throwable.getMessage())));
        }
        return null;
      });
  }

  private Future<CollectionExtension<Instance>> getInstanceCollectionExtension(String query, int offset, int limit,
    Map<String, String> okapiHeaders, Context vertxContext, List<String> moduleList) {

    CQLParameters<Instance> cqlParameters = new CQLParameters<>(query);
    cqlParameters.setComparator(InstanceComparator.get(cqlParameters.getCQLSortNode()));

    final MergeRequest<Instance> mergeRequest = new MergeRequest.MergeRequestBuilder<Instance>()
      .setLimit(limit)
      .setOffset(offset)
      .setHeaders(okapiHeaders)
      .setVertxContext(vertxContext)
      .setMuxCollectionMap(new LinkedHashMap<>())
      .build();

    return mergeSort(moduleList, cqlParameters, mergeRequest, CodexInterfaces.CODEX,
        InstanceCollectionParser::parseInstanceCollection).compose(instanceCollectionExtension -> {
          analyzeResult(mergeRequest.getMuxCollectionMap(), instanceCollectionExtension);
          return Future.succeededFuture(instanceCollectionExtension);
        });
  }

  @Override
  public void getCodexInstancesById(String id, String lang,
    Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> handler,
    Context vertxContext) {
    logger.info("Codex.mux getCodexInstancesById");

    OkapiParams okapiParams;
    try{
      okapiParams = new OkapiParams(okapiHeaders);
    }
    catch (IllegalArgumentException ex){
      handler.handle(
        Future.succeededFuture(Response.status(400)
          .header("Content-Type", "text/plain")
          .entity("Validation of okapi headers failed: " + ex.getMessage())
          .build()));
      return;
    }

    okapiClient.getModuleList(vertxContext, okapiParams, CodexInterfaces.CODEX)
      .compose(modules -> okapiClient.getOptionalObjects(vertxContext, okapiHeaders, modules,
        okapiHeaders.get(XOkapiHeaders.URL) + "/codex-instances/" + id , Instance.class))
      .map(optionalInstances -> {
        Optional<Instance> instance = optionalInstances
          .filter(Optional::isPresent)
          .map(Optional::get)
          .findFirst();
        if (instance.isEmpty()) {
          handler.handle(Future.succeededFuture(CodexInstances.GetCodexInstancesByIdResponse.respond404WithTextPlain(id)));
        } else {
          handler.handle(Future.succeededFuture(CodexInstances.GetCodexInstancesByIdResponse.respond200WithApplicationJson(
            instance.get())));
        }
        return null;
      })
      .otherwise(throwable -> {
        if(throwable instanceof GetModulesFailException) {
          handler.handle(Future.succeededFuture(CodexInstances.GetCodexInstancesByIdResponse.respond401WithTextPlain(
            throwable.getMessage())));
        } else{
          handler.handle(Future.succeededFuture(CodexInstances.GetCodexInstancesByIdResponse.respond500WithTextPlain(
            throwable.getMessage())));
        }
        return null;
      });
  }
}
