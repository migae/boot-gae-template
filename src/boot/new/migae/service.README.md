# single-service app

## Getting Started

1. Edit build.boot:
  a. change the project and version IDs to something suitable
  b. edit the dependency versions (e.g. for clojure, if RELEASE is not what you want).
2. Edit the docstring of the servlet (src/clj/myapp/core.clj) to reflect its function.
3. build
4. run

## Building

```
$ boot build target
```

## Running

```
$ boot gae/run
```

Navigate your browswer to `localhost:8080`.  Ctrl-c to exit.

To change the port, pass `--http-port=XXXX` to the gae/run task.  To see all run options:

```
$ boot gae/run -h`
```

Markdown doc: https://help.github.com/en/categories/writing-on-github