(ns kata-0200-tests.core 
  (:require [clojure.test :refer [deftest is testing]]))

; for tests, have a look at
; the test directory: it's not called src but test.
; Namespaces like this (ns kata-0200-tests.core)
; have a corresponding testing namespace named:
; (ns kata-0200-tests.core-test).
; Functions like a-fun
; have a corresponding test called
; a-fun-test.

; this will be run, if you do: lein run
; on the command line. It will also be the entry point for a fat jar...
(defn -main
  [& args]
  (println "Hello, World!"))

; this function has some tests in the test directory.
(defn a-fun [coll]
  (reduce + coll))

(comment
  (a-fun [1 2 3]))

; THIS TEST WILL BE RUN BY kaocha runner!
; when loading the file
(deftest a-fun-test-ignored-by-kaocha-here
  (testing "Context of the test assertions"
    (is (= 3 (a-fun [1 2])))))
