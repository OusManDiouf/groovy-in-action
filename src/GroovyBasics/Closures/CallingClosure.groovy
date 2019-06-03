package GroovyBasics.Closures


static def benchmark(int repeat, Closure worker) {

    def start = System.nanoTime()

    repeat.times { worker(it) }

    def stop = System.nanoTime()

    return stop - start
}

def slowBench = benchmark(100000) { i -> (int) i / 2 }
def fastBench = benchmark(100000) { it.intdiv(2) }
assert fastBench < slowBench



