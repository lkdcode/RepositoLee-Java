package lkdcode.app.benchmark;

import org.openjdk.jmh.annotations.*;
import task02.AutoBoxing;
import task02.Primitive;
import task02.PrimitiveParallel;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ForeachBenchmark {
    @Param({"1000000000", "10000000", "100000"})
    private int max;

    @Benchmark
    public long autoBoxingExecute() {
        return AutoBoxing.foreach(max);
    }

    @Benchmark
    public long primitiveExecute() {
        return Primitive.foreach(max);
    }

    @Benchmark
    public long primitiveParallelExecute() {
        return PrimitiveParallel.foreach(max);
    }

    @TearDown(Level.Invocation)
    public void tearDown() {
        System.gc();
    }
}