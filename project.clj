(defproject simple-om-draggable "0.1.0-SNAPSHOT"
  :description "A simple draggable wrapper component for Om"
  :url "https://github.com/cobalamin/simple-om-draggable"
  :license {:name "The MIT License (MIT)"
            :url "http://opensource.org/licenses/MIT"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2755"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.omcljs/om "0.8.8"]
                 [prismatic/om-tools "0.3.10"]]

  :plugins [[lein-cljsbuild "1.0.4"]]

  :source-paths ["src" "target/classes"]

  :clean-targets ["out/simple_om_draggable" "out/simple_om_draggable.js"]

  :cljsbuild {
    :builds [{:id "simple-om-draggable"
              :source-paths ["src"]
              :compiler {
                :output-to "out/simple_om_draggable.js"
                :output-dir "out"
                :optimizations :none
                :cache-analysis true
                :source-map true}}]})
