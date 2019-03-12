(def +project+ 'tmp/{{project}})
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :dependencies   '[[org.clojure/clojure "1.10.0" :scope "runtime"]
                   [com.google.appengine/appengine-java-sdk RELEASE :scope "provided" :extension "zip"]
                   [migae/boot-gae "0.2.1-SNAPSHOT" :scope "test"]])

(require '[migae.boot-gae :as gae]
         '[boot.task.built-in :as builtin])

(task-options!
 gae/run {:services [
{{#services}}
                             {:name "{{service}}" :wardir "{{wardir}}/target" :port {{port}}}
{{/services}}
	]})

