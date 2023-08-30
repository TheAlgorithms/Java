FROM gitpod/workspace-java-17:2023-08-30-14-07-38

USER root

RUN apt-get update \
  && apt-get install -y --no-install-recommends \
  clang-format=1:14.0-55~exp2 \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

USER gitpod
