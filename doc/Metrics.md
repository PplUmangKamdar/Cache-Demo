## Metrics
All metrics can be viewed at https://influxdb.lstqa.net for Chronograf and https://influxdb.lstqa.net:3000 for Grafana.  
More information about the default metrics in Spring and how to add custom metrics can be found at  
https://spring.io/blog/2018/03/16/micrometer-spring-boot-2-s-new-application-metrics-collector

### Enabling metrics during local development
Metrics can enabled by settings 
```
management.metrics.export.influx.enabled=true
```
in application.properties. After that they can be viewed at the address above.

### Overrides for enabling metrics for all other environments
nightly:
```
management.metrics.export.influx.enabled=true
management.metrics.export.influx.db=rbs-nightly
management.metrics.export.influx.userName=svc_rbs_nightly
management.metrics.export.influx.password==**********
```
perf:
```
management.metrics.export.influx.enabled=true
management.metrics.export.influx.db=rbs-perf
management.metrics.export.influx.userName=svc_rbs_perf
management.metrics.export.influx.password==**********
```
prod:
```
management.metrics.export.influx.enabled=true
management.metrics.export.influx.db=rbs-prod
management.metrics.export.influx.userName=svc_rbs_prod
management.metrics.export.influx.password==**********
```