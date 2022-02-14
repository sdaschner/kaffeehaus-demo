# use <git-repo-URL>#BRANCH to specify the Git repo
$ oc new-build  https://github.com/thikade/kaffeehaus-demo#openshift --name kaffeehaus  --name kaffeehaus -o yaml --dry-run  | tee buildconfig.yaml

$ oc apply -f buildconfig.yaml

# make K8s deployments use our imagestream
$ oc set image-lookup kaffeehaus