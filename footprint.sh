#!/bin/sh

. ./env.sh

ulimit -v 4194304 -n 4096 -u 63778

${SCALATRUNK}/bin/scala -J-Xmx1G -cp bin benchmarks.FibBench benchmarks.ScalaStream --footprint
${SCALATRUNK}/bin/scala -J-Xmx1G -cp bin benchmarks.FibBench benchmarks.ScalaGenerator --footprint
${SCALATRUNK}/bin/scala -J-Xmx1G -J-Xss104k -cp bin benchmarks.FibBench benchmarks.JavaThread --footprint
${SCALATRUNK}/bin/scala -J-Xmx3G -cp bin benchmarks.FibBench benchmarks.JavaCoroutine --footprint -v
