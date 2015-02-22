(defproject simple-om-draggable "0.1.0"
  :description "A simple draggable wrapper component for Om"
  :url "https://github.com/cobalamin/simple-om-draggable"
  :license {:name "The MIT License (MIT)"
            :url "http://opensource.org/licenses/MIT"}

  :signing {:gpg-key "C6FCEC07"}
  :scm {:name "git"
        :url "https://github.com/cobalamin/simple-om-draggable"}
  :deploy-repositories [["clojars" {:creds :gpg}]]

  :dependencies [[org.clojure/clojure "1.7.0-alpha5"]
                 [org.clojure/clojurescript "0.0-2913" :scope "provided"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.omcljs/om "0.8.8"]
                 [prismatic/om-tools "0.3.10"]]

  :plugins [[lein-cljsbuild "1.0.3"]]

  :source-paths ["src" "target/classes"]

  :clean-targets ["resources/public/out" "resources/public/example.js"]

  :cljsbuild {
    :builds [{:id "example"
              :source-paths ["src"]
              :compiler {
                :output-to "resources/public/example.js"
                :output-dir "resources/public/out"
                :optimizations :none
                :cache-analysis true
                :source-map true}}]})
