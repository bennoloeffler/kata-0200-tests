(ns kata-0200-tests.core-test
  (:require [kata-0200-tests.core :refer :all] ; test subject - use all
            [clojure.test :refer [deftest testing is]] ; this could be overwritten by power asserts [erdos.assert :refer [is are]]
            ; but power asserts are not needed with caocha, because of humane test output - TODO check...
            [clojure.spec.alpha :as s] ; kind of typing for data structures - may be used in expectations 
            [expectations.clojure.test ; more readable tests
             :refer [defexpect expect expecting in more-of more from-each
                     approximately between between' #_functionally
                     #_side-effects]]))

; just for conveniance 
; install "terminal notifier"
; see: https://cljdoc.org/d/lambdaisland/kaocha/1.67.1055/doc/plugin-notifier-desktop-notifications-

; run test continously by 
; cd to project dir
; then: 
; lein caocha

; run coverage by
; lein coverage
; Then:
; /Users/benno/projects/kata-bel/kata-0200-tests/target/coverage/index.html

; see the different options in the context menu to run 
; - one test,
; - one test namespace,
; - all tests in project.
; - failed tests again

; traditional test - batteries included with clojure lib.
(deftest a-fun-test
  (testing "FIXME, I may fail."
    (is (= 6 (a-fun [1 2 3]))))
    ; test to uncomment this...
    #_(is (=  :oops-i-only-gave-one-argument)))

; humane test output in caocha...
(deftest two-structures-compared
    (is (= {:key1 6 :key2 7 :8 9 :unkn [12 13 1]} 
           {:key1 6 :key2 7 :unkn [12 13 1] :8 9})))

;; mix'n'match libraries:
(deftest mixed ; traditional "is" with expectation...
  (is (= 2 (+ 1 1)))
  (expect even? (+ 1 11))) ; much nicer 

;; simple equality tests:
(deftest equality
  (expect 1 (* 1 1))
  (expect "foo" (str "f" "oo")))

;; the expected outcome can be a regular expression:
(defexpect regex-1
  (expect #"foo" "It's foobar!"))

;; since that has only a single expectation, it can be written more succinctly:
(defexpect regex-2 #"foo" "It's foobar!")

;; the expected outcome can be an exception type:
(defexpect divide-by-zero ArithmeticException (/ 12 0))

;; the expected outcome can be a predicate:
(defexpect no-elements empty? (list))

;; the expected outcome can be a type:
(defexpect named String (name :foo))

;; the expected outcome can be a Spec (require Clojure 1.9 or later):
(s/def ::value (s/and pos-int? #(< % 100)))
(defexpect small-value
  (expect ::value (* 13 4)))

;; if the actual value is a collection, 
; the expected outcome can be an element 
; or subset "in" that collection:
(defexpect collections
  (expect {:foo 1} (in {:foo 1 :cat 4}))
  (expect :foo (in #{:foo :bar}))
  (expect :foo (in [:bar :foo])))

;; just like clojure.test's testing macro to label groups of tests
;; you can use expecting to label groups of expectations (this uses
;; some of more advanced features listed below):
(defexpect grouped-behavior
  (expecting "numeric behavior"
             
             (expect (more-of [a b] string? a int? b) ["test" 42])
             (expect (more-of {:keys [a b]}
                              even? a
                              odd?  b)
                     {:a (* 2 13) :b (* 3 13)})
             
             (expect pos? (* -3 -5)))
  
  (expecting "string behavior"
             
             (expect (more int? even?) 42)
             (expect (more #"foo" 
                           "foobar" 
                           #(clojure.string/starts-with? % "f"))
                     (str "f" "oobar"))
             
             (expect even? (from-each [v (range 10)] (* 2 v)))
             (expect #"foo"
                     (from-each [s ["l" "d" "bar"]]
                                (str "foo" s)))))

(defexpect some-other-expecations
  (expect (between 4 9) 9)
  (expect (between' 4 9) 8) ; 9 won't do it...
  (expect (approximately 4 0.1) 3.9)


  )