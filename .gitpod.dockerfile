FROM gitpod/workspace-java-17:2023-07-20-19-56-24

USER root

RUN apt-get update \
  && apt-get install -y --no-install-recommends \
  clang-format=1:14.0-55~exp2 \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

USER gitpod
