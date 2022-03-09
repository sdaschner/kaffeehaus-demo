# use <git-repo-URL>#BRANCH to specify the Git repo
$ oc new-build  https://github.com/sdaschner/kaffeehaus-demo#main --name kaffeehaus  --name kaffeehaus -o yaml --dry-run  | tee buildconfig.yaml

$ oc apply -f buildconfig.yaml

# make K8s deployments use our imagestream
$ oc set image-lookup kaffeehaus