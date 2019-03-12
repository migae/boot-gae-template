(def +project+ 'tmp.gae/{{service}})
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :asset-paths #{"resources/public"}
 :resource-paths #{"src/clj"}
 :source-paths #{"config"}

 :repositories #(conj % ["maven-central" {:url "https://mvnrepository.com"}]
                      ["central" "https://repo1.maven.org/maven2/"])

 :dependencies   '[[org.clojure/clojure "1.10.0" :scope "runtime"]
                   [org.clojure/tools.reader "1.3.2"]
                   [org.clojure/tools.logging "0.4.1"]

                   [migae/boot-gae "0.2.1-SNAPSHOT" :scope "test"]

                   [javax.servlet/javax.servlet-api "3.1.0" :scope "provided"]

                   ;; this is for the GAE runtime (NB: scope provided):
                   [com.google.appengine/appengine-java-sdk RELEASE :scope "provided" :extension "zip"]

                   ;; this is for the GAE services like datastore (NB: scope runtime):
                   [com.google.appengine/appengine-api-1.0-sdk RELEASE :scope "runtime"]

                   [cheshire/cheshire "5.8.1"]

                   [compojure/compojure "1.6.1"]
                   [ring/ring-core "1.7.1"]
                   [ring/ring-servlet "1.7.1"]
                   [ring/ring-devel "1.7.1" :scope "test"]
                   [ring/ring-defaults "0.3.2"]
                   [fogus/ring-edn "0.3.0"]

                   [ns-tracker/ns-tracker "0.3.1"]
                   ])

(require '[migae.boot-gae :as gae])
;;         '[boot.task.built-in :as builtin])

(task-options!
 gae/install-service {:project     +project+
                      :version     +version+}
 pom  {:project     +project+
       :version     +version+
       :description "Sample uploader service for GAE app"
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask build
  "Configure and build servlet or service app"
  [k keep bool "keep intermediate .clj and .edn files"
   p prod bool "production build, without reloader"
   s servlet bool "build a servlet-based app DEPRECATED"
   v verbose bool "verbose"]
  (let [keep (or keep false)
        verbose (or verbose false)]
    (comp (gae/install-sdk)
          (gae/libs :verbose verbose)
          (gae/filters :keep (if prod false keep) :verbose verbose)
          (gae/servlets :keep (if prod false keep) :verbose verbose)
          (gae/logging :verbose verbose)
          (if prod identity (gae/reloader :keep keep :verbose verbose))
          ;; ;;(gae/config-service :unit-test unit-test :keep keep)
          (gae/webxml :verbose verbose :keep keep)
          (gae/appengine :verbose verbose :keep keep)
          (gae/build-sift) ;; :unit-test unit-test :keep keep)
          ;; (if servlet identity (gae/install-service))
          ;; (gae/install-service)
          (if prod identity (gae/keep-config))
          ;; (gae/assemble :keep keep)
          ;; (gae/config-app :keep keep)
          )))

