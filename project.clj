(defproject kata-0200-tests "0.1.0-SNAPSHOT"
  :description "example of kaocha test lib"
  :url ""
  :license {:name "WTFPL"
            :url  "http://www.wtfpl.net/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [com.github.seancorfield/expectations "2.0.160"]]
  :main ^:skip-aot kata-0200-tests.core
  :target-path "target/%s"

  ; reload and repeatedly run tests as soon as sourcecode is changed:
  ; $ lein kaocha
  
  ; see coverage of test (with cloverage)
  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner" "--plugin" "notifier" "--watch"]
            "coverage" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner" "--plugin" "cloverage"]}
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :kaocha {:dependencies [[lambdaisland/kaocha "1.67.1055"]
                                     [lambdaisland/kaocha-cloverage "1.0.75"]]}})
