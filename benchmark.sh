#!/bin/sh

. ./env.sh

${SCALATRUNK}/bin/scala -cp bin fibonacci.FibBench fibonacci.ScalaStream --benchmark 1000000
${SCALATRUNK}/bin/scala -cp bin fibonacci.FibBench fibonacci.ScalaGenerator --benchmark 10000000
${SCALATRUNK}/bin/scala -cp bin fibonacci.FibBench fibonacci.JavaThread --benchmark 100000
${SCALATRUNK}/bin/scala -cp bin fibonacci.FibBench fibonacci.JavaCoroutine --benchmark 10000000
