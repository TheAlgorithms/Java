FROM gitpod/workspace-java-21:2024-07-14-17-19-51

ENV LLVM_SCRIPT="tmp_llvm.sh"

RUN test ! -f  "$LLVM_SCRIPT" \
  && wget https://apt.llvm.org/llvm.sh -O "$LLVM_SCRIPT" \
  && chmod +x "$LLVM_SCRIPT"

USER root

RUN ./"$LLVM_SCRIPT" 16 \
  && apt-get update \
  && apt-get install -y --no-install-recommends \
  clang-format-16=1:16.0.6~++20231112100510+7cbf1a259152-1~exp1~20231112100554.106 \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

RUN ln -s "$(command -v clang-format-16)" "/usr/bin/clang-format"

USER gitpod

RUN rm "$LLVM_SCRIPT"
