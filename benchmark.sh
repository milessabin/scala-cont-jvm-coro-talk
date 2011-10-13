#!/bin/sh

. ./env.sh

${SCALATRUNK}/bin/scala -cp bin benchmarks.FibBench benchmarks.ScalaStream --benchmark 1000000
${SCALATRUNK}/bin/scala -cp bin benchmarks.FibBench benchmarks.ScalaGenerator --benchmark 10000000
${SCALATRUNK}/bin/scala -cp bin benchmarks.FibBench benchmarks.JavaThread --benchmark 100000
${SCALATRUNK}/bin/scala -cp bin benchmarks.FibBench benchmarks.JavaCoroutine --benchmark 10000000
