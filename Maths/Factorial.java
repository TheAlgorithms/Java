package org.apache.zookeeper.server;

import org.apache.zookeeper.metrics.Counter;
import org.apache.zookeeper.metrics.MetricsContext;
import org.apache.zookeeper.metrics.MetricsContext.DetailLevel;
import org.apache.zookeeper.metrics.MetricsProvider;
import org.apache.zookeeper.metrics.Summary;
import org.apache.zookeeper.metrics.SummarySet;
import org.apache.zookeeper.metrics.impl.DefaultMetricsProvider;
import org.apache.zookeeper.metrics.impl.NullMetricsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServerMetrics {

    private static final Logger LOG = LoggerFactory.getLogger(ServerMetrics.class);

    /**
     * Dummy instance useful for tests.
     */
    public static final ServerMetrics NULL_METRICS = new ServerMetrics(NullMetricsProvider.INSTANCE);

    /**
     * Dummy instance useful for tests.
     */
    public static final ServerMetrics DEFAULT_METRICS_FOR_TESTS = new ServerMetrics(new DefaultMetricsProvider());

    /**
     * Real instance used for tracking server side metrics. The final value is
     * assigned after the {@link MetricsProvider} bootstrap.
     */
    private static volatile ServerMetrics CURRENT = DEFAULT_METRICS_FOR_TESTS;

    /**
     * Access current ServerMetrics.
     *
     * @return a reference to the current Metrics
     */
    public static ServerMetrics getMetrics() {
        return CURRENT;
    }

    public static void metricsProviderInitialized(MetricsProvider metricsProvider) {
        LOG.info("ServerMetrics initialized with provider {}", metricsProvider);
        CURRENT = new ServerMetrics(metricsProvider);
    }

    private ServerMetrics(MetricsProvider metricsProvider) {
        this.metricsProvider = metricsProvider;
        MetricsContext metricsContext = this.metricsProvider.getRootContext();

        FSYNC_TIME = metricsContext.getSummary("fsynctime", DetailLevel.BASIC);

        SNAPSHOT_TIME = metricsContext.getSummary("snapshottime", DetailLevel.BASIC);
        DB_INIT_TIME = metricsContext.getSummary("dbinittime", DetailLevel.BASIC);
        READ_LATENCY = metricsContext.getSummary("readlatency", DetailLevel.ADVANCED);
        UPDATE_LATENCY = metricsContext.getSummary("updatelatency", DetailLevel.ADVANCED);
        PROPAGATION_LATENCY = metricsContext.getSummary("propagation_latency", DetailLevel.ADVANCED);
        FOLLOWER_SYNC_TIME = metricsContext.getSummary("follower_sync_time", DetailLevel.BASIC);
        ELECTION_TIME = metricsContext.getSummary("election_time", DetailLevel.BASIC);
        LOOKING_COUNT = metricsContext.getCounter("looking_count");
        DIFF_COUNT = metricsContext.getCounter("diff_count");
        SNAP_COUNT = metricsContext.getCounter("snap_count");
        COMMIT_COUNT = metricsContext.getCounter("commit_count");
        CONNECTION_REQUEST_COUNT = metricsContext.getCounter("connection_request_count");
        CONNECTION_TOKEN_DEFICIT = metricsContext.getSummary("connection_token_deficit", DetailLevel.BASIC);
        CONNECTION_REJECTED = metricsContext.getCounter("connection_rejected");

        INFLIGHT_SNAP_COUNT = metricsContext.getSummary("inflight_snap_count", DetailLevel.BASIC);
        INFLIGHT_DIFF_COUNT = metricsContext.getSummary("inflight_diff_count", DetailLevel.BASIC);

        WRITE_PER_NAMESPACE = metricsContext.getSummarySet("write_per_namespace", DetailLevel.BASIC);
        READ_PER_NAMESPACE = metricsContext.getSummarySet("read_per_namespace", DetailLevel.BASIC);

        BYTES_RECEIVED_COUNT = metricsContext.getCounter("bytes_received_count");
        UNRECOVERABLE_ERROR_COUNT = metricsContext.getCounter("unrecoverable_error_count");

        NODE_CREATED_WATCHER = metricsContext.getSummary("node_created_watch_count", DetailLevel.BASIC);
        NODE_DELETED_WATCHER = metricsContext.getSummary("node_deleted_watch_count", DetailLevel.BASIC);
        NODE_CHANGED_WATCHER = metricsContext.getSummary("node_changed_watch_count", DetailLevel.BASIC);
        NODE_CHILDREN_WATCHER = metricsContext.getSummary("node_children_watch_count", DetailLevel.BASIC);

        /*
         * Number of dead watchers in DeadWatcherListener
         */
        ADD_DEAD_WATCHER_STALL_TIME = metricsContext.getCounter("add_dead_watcher_stall_time");
        DEAD_WATCHERS_QUEUED = metricsContext.getCounter("dead_watchers_queued");
        DEAD_WATCHERS_CLEARED = metricsContext.getCounter("dead_watchers_cleared");
        DEAD_WATCHERS_CLEANER_LATENCY = metricsContext.getSummary("dead_watchers_cleaner_latency", DetailLevel.ADVANCED);

        RESPONSE_PACKET_CACHE_HITS = metricsContext.getCounter("response_packet_cache_hits");
        RESPONSE_PACKET_CACHE_MISSING = metricsContext.getCounter("response_packet_cache_misses");
        RESPONSE_PACKET_GET_CHILDREN_CACHE_HITS = metricsContext.getCounter("response_packet_get_children_cache_hits");
        RESPONSE_PACKET_GET_CHILDREN_CACHE_MISSING = metricsContext.getCounter("response_packet_get_children_cache_misses");

        ENSEMBLE_AUTH_SUCCESS = metricsContext.getCounter("ensemble_auth_success");

        ENSEMBLE_AUTH_FAIL = metricsContext.getCounter("ensemble_auth_fail");

        ENSEMBLE_AUTH_SKIP = metricsContext.getCounter("ensemble_auth_skip");

        PREP_PROCESSOR_QUEUE_TIME = metricsContext.getSummary("prep_processor_queue_time_ms", DetailLevel.ADVANCED);
        PREP_PROCESSOR_QUEUE_SIZE = metricsContext.getSummary("prep_processor_queue_size", DetailLevel.BASIC);
        PREP_PROCESSOR_QUEUED = metricsContext.getCounter("prep_processor_request_queued");
        OUTSTANDING_CHANGES_QUEUED = metricsContext.getCounter("outstanding_changes_queued");
        OUTSTANDING_CHANGES_REMOVED = metricsContext.getCounter("outstanding_changes_removed");
        PREP_PROCESS_TIME = metricsContext.getSummary("prep_process_time", DetailLevel.BASIC);
        PROPOSAL_PROCESS_TIME = metricsContext.getSummary("proposal_process_time", DetailLevel.BASIC);
        CLOSE_SESSION_PREP_TIME = metricsContext.getSummary("close_session_prep_time", DetailLevel.ADVANCED);

        REVALIDATE_COUNT = metricsContext.getCounter("revalidate_count");
        CONNECTION_DROP_COUNT = metricsContext.getCounter("connection_drop_count");
        CONNECTION_REVALIDATE_COUNT = metricsContext.getCounter("connection_revalidate_count");

        // Expiry queue stats
        SESSIONLESS_CONNECTIONS_EXPIRED = metricsContext.getCounter("sessionless_connections_expired");
        STALE_SESSIONS_EXPIRED = metricsContext.getCounter("stale_sessions_expired");

        /*
         * Number of requests that are in the session queue.
         */
        REQUESTS_IN_SESSION_QUEUE = metricsContext.getSummary("requests_in_session_queue", DetailLevel.BASIC);
        PENDING_SESSION_QUEUE_SIZE = metricsContext.getSummary("pending_session_queue_size", DetailLevel.BASIC);
        /*
         * Consecutive number of read requests that are in the session queue right after a commit request.
         */
        READS_AFTER_WRITE_IN_SESSION_QUEUE = metricsContext.getSummary("reads_after_write_in_session_queue", DetailLevel.BASIC);
        READ_ISSUED_FROM_SESSION_QUEUE = metricsContext.getSummary("reads_issued_from_session_queue", DetailLevel.BASIC);
        SESSION_QUEUES_DRAINED = metricsContext.getSummary("session_queues_drained", DetailLevel.BASIC);

        TIME_WAITING_EMPTY_POOL_IN_COMMIT_PROCESSOR_READ = metricsContext.getSummary("time_waiting_empty_pool_in_commit_processor_read_ms", DetailLevel.BASIC);
        WRITE_BATCH_TIME_IN_COMMIT_PROCESSOR = metricsContext.getSummary("write_batch_time_in_commit_processor", DetailLevel.BASIC);

        CONCURRENT_REQUEST_PROCESSING_IN_COMMIT_PROCESSOR = metricsContext.getSummary("concurrent_request_processing_in_commit_processor", DetailLevel.BASIC);

        READS_QUEUED_IN_COMMIT_PROCESSOR = metricsContext.getSummary("read_commit_proc_req_queued", DetailLevel.BASIC);
        WRITES_QUEUED_IN_COMMIT_PROCESSOR = metricsContext.getSummary("write_commit_proc_req_queued", DetailLevel.BASIC);
        COMMITS_QUEUED_IN_COMMIT_PROCESSOR = metricsContext.getSummary("commit_commit_proc_req_queued", DetailLevel.BASIC);
        COMMITS_QUEUED = metricsContext.getCounter("request_commit_queued");
        READS_ISSUED_IN_COMMIT_PROC = metricsContext.getSummary("read_commit_proc_issued", DetailLevel.BASIC);
        WRITES_ISSUED_IN_COMMIT_PROC = metricsContext.getSummary("write_commit_proc_issued", DetailLevel.BASIC);

        THROTTLED_OPS = metricsContext.getCounter("throttled_ops");

        /**
         * Time spent by a read request in the commit processor.
         */
        READ_COMMITPROC_TIME = metricsContext.getSummary("read_commitproc_time_ms", DetailLevel.ADVANCED);

        /**
         * Time spent by a write request in the commit processor.
         */
        WRITE_COMMITPROC_TIME = metricsContext.getSummary("write_commitproc_time_ms", DetailLevel.ADVANCED);

        /**
         * Time spent by a committed request, for a locally issued write, in the
         * commit processor.
         */
        LOCAL_WRITE_COMMITTED_TIME = metricsContext.getSummary("local_write_committed_time_ms", DetailLevel.ADVANCED);

        /**
         * Time spent by a committed request for a write, issued by other server, in the
         * commit processor.
         */
        SERVER_WRITE_COMMITTED_TIME = metricsContext.getSummary("server_write_committed_time_ms", DetailLevel.ADVANCED);

        COMMIT_PROCESS_TIME = metricsContext.getSummary("commit_process_time", DetailLevel.BASIC);

        /**
         * Observer Master processing metrics.
         */
        OM_PROPOSAL_PROCESS_TIME = metricsContext.getSummary("om_proposal_process_time_ms", DetailLevel.ADVANCED);
        OM_COMMIT_PROCESS_TIME = metricsContext.getSummary("om_commit_process_time_ms", DetailLevel.ADVANCED);

        /**
         * Time spent by the final processor. This is tracked in the commit processor.
         */
        READ_FINAL_PROC_TIME = metricsContext.getSummary("read_final_proc_time_ms", DetailLevel.ADVANCED);
        WRITE_FINAL_PROC_TIME = metricsContext.getSummary("write_final_proc_time_ms", DetailLevel.ADVANCED);

        PROPOSAL_LATENCY = metricsContext.getSummary("proposal_latency", DetailLevel.ADVANCED);
        PROPOSAL_ACK_CREATION_LATENCY = metricsContext.getSummary("proposal_ack_creation_latency", DetailLevel.ADVANCED);
        COMMIT_PROPAGATION_LATENCY = metricsContext.getSummary("commit_propagation_latency", DetailLevel.ADVANCED);
        LEARNER_PROPOSAL_RECEIVED_COUNT = metricsContext.getCounter("learner_proposal_received_count");
        LEARNER_COMMIT_RECEIVED_COUNT = metricsContext.getCounter("learner_commit_received_count");

        /**
         * Learner handler quorum packet metrics.
         */
        LEARNER_HANDLER_QP_SIZE = metricsContext.getSummarySet("learner_handler_qp_size", DetailLevel.BASIC);
        LEARNER_HANDLER_QP_TIME = metricsContext.getSummarySet("learner_handler_qp_time_ms", DetailLevel.ADVANCED);

        STARTUP_TXNS_LOADED = metricsContext.getSummary("startup_txns_loaded", DetailLevel.BASIC);
        STARTUP_TXNS_LOAD_TIME = metricsContext.getSummary("startup_txns_load_time", DetailLevel.BASIC);
        STARTUP_SNAP_LOAD_TIME = metricsContext.getSummary("startup_snap_load_time", DetailLevel.BASIC);

        SYNC_PROCESSOR_QUEUE_AND_FLUSH_TIME = metricsContext.getSummary("sync_processor_queue_and_flush_time_ms", DetailLevel.ADVANCED);
        SYNC_PROCESSOR_QUEUE_SIZE = metricsContext.getSummary("sync_processor_queue_size", DetailLevel.BASIC);
        SYNC_PROCESSOR_QUEUED = metricsContext.getCounter("sync_processor_request_queued");
        SYNC_PROCESSOR_QUEUE_TIME = metricsContext.getSummary("sync_processor_queue_time_ms", DetailLevel.ADVANCED);
        SYNC_PROCESSOR_FLUSH_TIME = metricsContext.getSummary("sync_processor_queue_flush_time_ms", DetailLevel.ADVANCED);
        SYNC_PROCESS_TIME = metricsContext.getSummary("sync_process_time", DetailLevel.BASIC);

        BATCH_SIZE = metricsContext.getSummary("sync_processor_batch_size", DetailLevel.BASIC);

        QUORUM_ACK_LATENCY = metricsContext.getSummary("quorum_ack_latency", DetailLevel.ADVANCED);
        ACK_LATENCY = metricsContext.getSummarySet("ack_latency", DetailLevel.ADVANCED);
        PROPOSAL_COUNT = metricsContext.getCounter("proposal_count");
        QUIT_LEADING_DUE_TO_DISLOYAL_VOTER = metricsContext.getCounter("quit_leading_due_to_disloyal_voter");

        STALE_REQUESTS = metricsContext.getCounter("stale_requests");
        STALE_REQUESTS_DROPPED = metricsContext.getCounter("stale_requests_dropped");
        STALE_REPLIES = metricsContext.getCounter("stale_replies");
        REQUEST_THROTTLE_QUEUE_TIME = metricsContext.getSummary("request_throttle_queue_time_ms", DetailLevel.ADVANCED);
        REQUEST_THROTTLE_WAIT_COUNT = metricsContext.getCounter("request_throttle_wait_count");
        LARGE_REQUESTS_REJECTED = metricsContext.getCounter("large_requests_rejected");

        NETTY_QUEUED_BUFFER = metricsContext.getSummary("netty_queued_buffer_capacity", DetailLevel.BASIC);

        DIGEST_MISMATCHES_COUNT = metricsContext.getCounter("digest_mismatches_count");

        LEARNER_REQUEST_PROCESSOR_QUEUE_SIZE = metricsContext.getSummary("learner_request_processor_queue_size", DetailLevel.BASIC);

        UNSUCCESSFUL_HANDSHAKE = metricsContext.getCounter("unsuccessful_handshake");
        INSECURE_ADMIN = metricsContext.getCounter("insecure_admin_count");
        TLS_HANDSHAKE_EXCEEDED = metricsContext.getCounter("tls_handshake_exceeded");

        CNXN_CLOSED_WITHOUT_ZK_SERVER_RUNNING = metricsContext.getCounter("cnxn_closed_without_zk_server_running");

        SKIP_LEARNER_REQUEST_TO_NEXT_PROCESSOR_COUNT = metricsContext.getCounter("skip_learner_request_to_next_processor_count");

        SOCKET_CLOSING_TIME = metricsContext.getSummary("socket_closing_time", DetailLevel.BASIC);

        REQUESTS_NOT_FORWARDED_TO_COMMIT_PROCESSOR = metricsContext.getCounter(
                "requests_not_forwarded_to_commit_processor");

        RESPONSE_BYTES = metricsContext.getCounter("response_bytes");
        WATCH_BYTES = metricsContext.getCounter("watch_bytes");

        JVM_PAUSE_TIME = metricsContext.getSummary("jvm_pause_time_ms", DetailLevel.ADVANCED);
    }

    /**
     * Txnlog fsync time
     */
    public final Summary FSYNC_TIME;

    /**
     * Snapshot writing time
     */
    public final Summary SNAPSHOT_TIME;

    /**
     * Db init time (snapshot loading + txnlog replay)
     */
    public final Summary DB_INIT_TIME;

    /**
     * Stats for read request. The timing start from when the server see the
     * request until it leave final request processor.
     */
    public final Summary READ_LATENCY;

    /**
     * Stats for request that need quorum voting. Timing is the same as read
     * request. We only keep track of stats for request that originated from
     * this machine only.
     */
    public final Summary UPDATE_LATENCY;

    /**
     * Stats for all quorum request. The timing start from when the leader see
     * the request until it reach the learner.
     */
    public final Summary PROPAGATION_LATENCY;

    public final Summary FOLLOWER_SYNC_TIME;

    public final Summary ELECTION_TIME;

    public final Counter LOOKING_COUNT;
    public final Counter DIFF_COUNT;
    public final Counter SNAP_COUNT;
    public final Counter COMMIT_COUNT;
    public final Counter CONNECTION_REQUEST_COUNT;

    public final Counter REVALIDATE_COUNT;
    public final Counter CONNECTION_DROP_COUNT;
    public final Counter CONNECTION_REVALIDATE_COUNT;

    // Expiry queue stats
    public final Counter SESSIONLESS_CONNECTIONS_EXPIRED;
    public final Counter STALE_SESSIONS_EXPIRED;

    // Connection throttling related
    public final Summary CONNECTION_TOKEN_DEFICIT;
    public final Counter CONNECTION_REJECTED;

    public final Summary INFLIGHT_SNAP_COUNT;
    public final Summary INFLIGHT_DIFF_COUNT;

    public final Counter UNRECOVERABLE_ERROR_COUNT;
    public final SummarySet WRITE_PER_NAMESPACE;
    public final SummarySet READ_PER_NAMESPACE;
    public final Counter BYTES_RECEIVED_COUNT;

    public final Summary PREP_PROCESSOR_QUEUE_TIME;
    public final Summary PREP_PROCESSOR_QUEUE_SIZE;
    public final Counter PREP_PROCESSOR_QUEUED;
    public final Counter OUTSTANDING_CHANGES_QUEUED;
    public final Counter OUTSTANDING_CHANGES_REMOVED;
    public final Summary PREP_PROCESS_TIME;
    public final Summary PROPOSAL_PROCESS_TIME;
    public final Summary CLOSE_SESSION_PREP_TIME;

    public final Summary PROPOSAL_LATENCY;
    public final Summary PROPOSAL_ACK_CREATION_LATENCY;
    public final Summary COMMIT_PROPAGATION_LATENCY;
    public final Counter LEARNER_PROPOSAL_RECEIVED_COUNT;
    public final Counter LEARNER_COMMIT_RECEIVED_COUNT;

    public final Summary STARTUP_TXNS_LOADED;
    public final Summary STARTUP_TXNS_LOAD_TIME;
    public final Summary STARTUP_SNAP_LOAD_TIME;

    public final Summary SYNC_PROCESSOR_QUEUE_AND_FLUSH_TIME;
    public final Summary SYNC_PROCESSOR_QUEUE_SIZE;
    public final Counter SYNC_PROCESSOR_QUEUED;
    public final Summary SYNC_PROCESSOR_QUEUE_TIME;
    public final Summary SYNC_PROCESSOR_FLUSH_TIME;
    public final Summary SYNC_PROCESS_TIME;

    public final Summary BATCH_SIZE;

    public final Summary QUORUM_ACK_LATENCY;
    public final SummarySet ACK_LATENCY;
    public final Counter PROPOSAL_COUNT;
    public final Counter QUIT_LEADING_DUE_TO_DISLOYAL_VOTER;

    /**
     * Fired watcher stats.
     */
    public final Summary NODE_CREATED_WATCHER;
    public final Summary NODE_DELETED_WATCHER;
    public final Summary NODE_CHANGED_WATCHER;
    public final Summary NODE_CHILDREN_WATCHER;

    /*
     * Number of dead watchers in DeadWatcherListener
     */
    public final Counter ADD_DEAD_WATCHER_STALL_TIME;
    public final Counter DEAD_WATCHERS_QUEUED;
    public final Counter DEAD_WATCHERS_CLEARED;
    public final Summary DEAD_WATCHERS_CLEANER_LATENCY;

    /*
     * Response cache hit and miss metrics.
     */
    public final Counter RESPONSE_PACKET_CACHE_HITS;
    public final Counter RESPONSE_PACKET_CACHE_MISSING;
    public final Counter RESPONSE_PACKET_GET_CHILDREN_CACHE_HITS;
    public final Counter RESPONSE_PACKET_GET_CHILDREN_CACHE_MISSING;

    /**
     * Learner handler quorum packet metrics.
     */
    public final SummarySet LEARNER_HANDLER_QP_SIZE;
    public final SummarySet LEARNER_HANDLER_QP_TIME;

    /*
     * Number of requests that are in the session queue.
     */
    public final Summary REQUESTS_IN_SESSION_QUEUE;
    public final Summary PENDING_SESSION_QUEUE_SIZE;
    /*
     * Consecutive number of read requests that are in the session queue right after a commit request.
     */
    public final Summary READS_AFTER_WRITE_IN_SESSION_QUEUE;
    public final Summary READ_ISSUED_FROM_SESSION_QUEUE;
    public final Summary SESSION_QUEUES_DRAINED;

    public final Summary TIME_WAITING_EMPTY_POOL_IN_COMMIT_PROCESSOR_READ;
    public final Summary WRITE_BATCH_TIME_IN_COMMIT_PROCESSOR;

    public final Summary CONCURRENT_REQUEST_PROCESSING_IN_COMMIT_PROCESSOR;

    public final Summary READS_QUEUED_IN_COMMIT_PROCESSOR;
    public final Summary WRITES_QUEUED_IN_COMMIT_PROCESSOR;
    public final Summary COMMITS_QUEUED_IN_COMMIT_PROCESSOR;
    public final Counter COMMITS_QUEUED;
    public final Summary READS_ISSUED_IN_COMMIT_PROC;
    public final Summary WRITES_ISSUED_IN_COMMIT_PROC;

    // Request op throttling related
    public final Counter THROTTLED_OPS;

    /**
     * Time spent by a read request in the commit processor.
     */
    public final Summary READ_COMMITPROC_TIME;

    /**
     * Time spent by a write request in the commit processor.
     */
    public final Summary WRITE_COMMITPROC_TIME;

    /**
     * Time spent by a committed request, for a locally issued write, in the
     * commit processor.
     */
    public final Summary LOCAL_WRITE_COMMITTED_TIME;

    /**
     * Time spent by a committed request for a write, issued by other server, in the
     * commit processor.
     */
    public final Summary SERVER_WRITE_COMMITTED_TIME;

    public final Summary COMMIT_PROCESS_TIME;

    /**
     * Observer Master processing metrics.
     */
    public final Summary OM_PROPOSAL_PROCESS_TIME;
    public final Summary OM_COMMIT_PROCESS_TIME;

    /**
     * Time spent by the final processor. This is tracked in the commit processor.
     */
    public final Summary READ_FINAL_PROC_TIME;
    public final Summary WRITE_FINAL_PROC_TIME;

    /*
     * Number of successful matches of expected ensemble name in EnsembleAuthenticationProvider.
     */
    public final Counter ENSEMBLE_AUTH_SUCCESS;

    /*
     * Number of unsuccessful matches of expected ensemble name in EnsembleAuthenticationProvider.
     */
    public final Counter ENSEMBLE_AUTH_FAIL;

    /*
     * Number of client auth requests with no ensemble set in EnsembleAuthenticationProvider.
     */
    public final Counter ENSEMBLE_AUTH_SKIP;

    public final Counter STALE_REQUESTS;
    public final Counter STALE_REQUESTS_DROPPED;
    public final Counter STALE_REPLIES;
    public final Summary REQUEST_THROTTLE_QUEUE_TIME;
    public final Counter REQUEST_THROTTLE_WAIT_COUNT;
    public final Counter LARGE_REQUESTS_REJECTED;

    public final Summary NETTY_QUEUED_BUFFER;

    // Total number of digest mismatches that are observed when applying
    // txns to data tree.
    public final Counter DIGEST_MISMATCHES_COUNT;

    public final Summary LEARNER_REQUEST_PROCESSOR_QUEUE_SIZE;

    public final Counter UNSUCCESSFUL_HANDSHAKE;

    /*
     * Number of insecure connections to admin port
     */
    public final Counter INSECURE_ADMIN;

    public final Counter TLS_HANDSHAKE_EXCEEDED;

    public final Counter CNXN_CLOSED_WITHOUT_ZK_SERVER_RUNNING;

    public final Counter SKIP_LEARNER_REQUEST_TO_NEXT_PROCESSOR_COUNT;

    public final Summary SOCKET_CLOSING_TIME;

    public final Counter REQUESTS_NOT_FORWARDED_TO_COMMIT_PROCESSOR;

    /**
     *  Number of response/watch bytes written to clients.
     */
    public final Counter RESPONSE_BYTES;
    public final Counter WATCH_BYTES;

    public final Summary JVM_PAUSE_TIME;

    private final MetricsProvider metricsProvider;

    public void resetAll() {
        metricsProvider.resetAllValues();
    }

    public MetricsProvider getMetricsProvider() {
        return metricsProvider;
    }

}
