(def project 'migae/boot-template)
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"src"}
          ;; uncomment this if you write tests for your template:
          ;; :source-paths   #{"test"}
          :dependencies   '[[org.clojure/clojure "RELEASE"]
                            [boot/new "RELEASE"]
                            [adzerk/boot-test "RELEASE" :scope "test"]])

(require '[adzerk.boot-test :refer [test]]
         '[boot.new :refer [new]])

(task-options!
 pom {:project     project
      :version     version
      :description "FIXME: write description"
      :url         "http://example/FIXME"
      :scm         {:url "https://github.com/yourname/boot-gae-template"}
      :license     {"Eclipse Public License"
                    "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask locally
  "Build and install the project locally."
  []
  (comp (pom) (jar) (install)))

(deftask monitor
  "watch etc."
  []
  (comp (watch)
        (notify :audible true)))
