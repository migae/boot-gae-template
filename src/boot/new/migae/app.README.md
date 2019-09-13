# multi-service app

This application is composed of a collection of micro-services. Each
service has a dedicated directory under the application root
directory; this will be used as the WAR directory at runtime.

Each service has an independent structure, including a dedicated build.boot file.

## Getting Started

1. Edit the build.boot file in the app root directory and in each service subdirectory:
  a. change the project and version IDs to something suitable
  b. edit the dependency versions (e.g. for clojure, if RELEASE is not what you want).
2. For each service, edit the docstring of the servlet (myservice/clj/myapp/core.clj) to reflect its function.
3. build each service separately
4. run from the app root directory

## Building

```
$ <approot> $ cd myservice
$ <approot/myservice> $ boot build target
;; repeat for each service
```

## Running

```
$ <approot> $ boot gae/run
```

Each microservice will run on a different port.  The ports are passed
 to the gae/run task via a services map containing three keys: :name,
 :wardir, and :port.  For convenience these are specified as
 `task-options` in the build.boot file; edit it to change the ports.

Navigate your browswer to `localhost:<port>`, where <port> is the one specified for the service you wish to access.

To see all run options: `$ boot gae/run -h`

Markdown doc: https://help.github.com/en/categories/writing-on-github