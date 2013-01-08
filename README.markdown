> run
[info] Running org.example.Runner
[info]  0% Scenario{vm=java, trial=0, benchmark=WithNoEscape, EA=-XX:+DoEscapeAnalysis, server=-server} 2181525.72 ns;?=10615.54 ns @ 3 trials
[info] 25% Scenario{vm=java, trial=0, benchmark=WithEscape, EA=-XX:+DoEscapeAnalysis, server=-server} 9353994.74 ns; ?=49447.01 ns @ 3 trials
[info] 50% Scenario{vm=java, trial=0, benchmark=WithNoEscape, EA=-XX:-DoEscapeAnalysis, server=-server} 9221584.07 ns;?=659814.32 ns @ 10 trials
[info] 75% Scenario{vm=java, trial=0, benchmark=WithEscape, EA=-XX:-DoEscapeAnalysis, server=-server} 9235828.05 ns; ?=48667.01 ns @ 3 trials
[info]
[info]    benchmark                    EA   ms linear runtime
[info] WithNoEscape -XX:+DoEscapeAnalysis 2.18 ======
[info] WithNoEscape -XX:-DoEscapeAnalysis 9.22 =============================
[info]   WithEscape -XX:+DoEscapeAnalysis 9.35 ==============================
[info]   WithEscape -XX:-DoEscapeAnalysis 9.24 =============================
[info]
[info] vm: java
[info] trial: 0
[info] server: -server
[success] Total time: 44 s, completed Jan 8, 2013 6:27:00 PM