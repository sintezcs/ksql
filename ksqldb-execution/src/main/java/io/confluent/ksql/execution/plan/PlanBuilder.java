/*
 * Copyright 2019 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.execution.plan;

import io.confluent.ksql.GenericKey;
import org.apache.kafka.streams.kstream.Windowed;

/**
 * A visitor interface for building a query from an execution plan. There is a single
 * visit method for each execution step type (final implementations of ExecutionStep).
 * Implementers typically implement the visit methods by applying the transformation
 * specified in the step to the stream/table(s) returned by visiting the source steps.
 */
public interface PlanBuilder {
  <K> KStreamHolder<K> visitStreamFilter(StreamFilter<K> streamFilter);

  <K> KGroupedStreamHolder visitStreamGroupBy(StreamGroupBy<K> streamGroupBy);

  KGroupedStreamHolder visitStreamGroupByKey(StreamGroupByKey streamGroupByKey);

  KTableHolder<GenericKey> visitStreamAggregate(StreamAggregate streamAggregate);

  <K> KStreamHolder<K> visitStreamSelect(StreamSelect<K> streamSelect);

  <K> KStreamHolder<K> visitFlatMap(StreamFlatMap<K> streamFlatMap);

  KStreamHolder<GenericKey> visitStreamSelectKey(StreamSelectKeyV1 streamSelectKey);

  <K> KStreamHolder<K> visitStreamSelectKey(StreamSelectKey<K> streamSelectKey);

  <K> KStreamHolder<K> visitStreamSink(StreamSink<K> streamSink);

  KStreamHolder<GenericKey> visitStreamSource(StreamSource streamSource);

  KStreamHolder<Windowed<GenericKey>> visitWindowedStreamSource(
      WindowedStreamSource windowedStreamSource);

  <K> KStreamHolder<K> visitStreamStreamJoin(StreamStreamJoin<K> streamStreamJoin);

  <K> KStreamHolder<K> visitStreamTableJoin(StreamTableJoin<K> streamTableJoin);

  KTableHolder<GenericKey> visitTableSource(TableSource tableSource);

  KTableHolder<Windowed<GenericKey>> visitWindowedTableSource(
      WindowedTableSource windowedTableSource);

  KTableHolder<Windowed<GenericKey>> visitStreamWindowedAggregate(
      StreamWindowedAggregate streamWindowedAggregate);

  KTableHolder<GenericKey> visitTableAggregate(TableAggregate tableAggregate);

  <K> KTableHolder<K> visitTableFilter(TableFilter<K> tableFilter);

  <K> KGroupedTableHolder visitTableGroupBy(TableGroupBy<K> tableGroupBy);

  <K> KTableHolder<K> visitTableSelect(TableSelect<K> tableSelect);

  <K> KTableHolder<K> visitTableSelectKey(TableSelectKey<K> tableSelectKey);

  <K> KTableHolder<K> visitTableSink(TableSink<K> tableSink);

  <K> KTableHolder<K> visitTableSuppress(TableSuppress<K> tableSuppress);

  <K> KTableHolder<K> visitTableTableJoin(TableTableJoin<K> tableTableJoin);
}
