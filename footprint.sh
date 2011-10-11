#!/bin/sh

ulimit -v 4194304 -n 4096 -u 63778
export JAVA_HOME=${COROJDK}
export PATH=${JAVA_HOME}/bin:${PATH}

${SCALATRUNK}/bin/scala -J-Xmx1G -cp bin fibonacci.FibBench fibonacci.ScalaStream --footprint
${SCALATRUNK}/bin/scala -J-Xmx1G -cp bin fibonacci.FibBench fibonacci.ScalaGenerator --footprint
${SCALATRUNK}/bin/scala -J-Xmx1G -J-Xss104k -cp bin fibonacci.FibBench fibonacci.JavaThread --footprint
${SCALATRUNK}/bin/scala -J-Xmx3G -cp bin fibonacci.FibBench fibonacci.JavaCoroutine --footprint -v
