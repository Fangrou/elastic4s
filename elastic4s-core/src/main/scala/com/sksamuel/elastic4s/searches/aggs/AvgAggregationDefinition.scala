package com.sksamuel.elastic4s.searches.aggs

import com.sksamuel.elastic4s.script.ScriptDefinition
import com.sksamuel.elastic4s.searches.aggs.pipeline.PipelineAggregationDefinition
import com.sksamuel.exts.OptionImplicits._

case class AvgAggregationDefinition(name: String,
                                    missing: Option[String] = None,
                                    field: Option[String] = None,
                                    script: Option[ScriptDefinition] = None,
                                    pipelines: Seq[PipelineAggregationDefinition] = Nil,
                                    subaggs: Seq[AggregationDefinition] = Nil,
                                    metadata: Map[String, AnyRef] = Map.empty)
  extends AggregationDefinition {

  type T = AvgAggregationDefinition

  def field(field: String): AvgAggregationDefinition = copy(field = field.some)
  def missing(missing: String): AvgAggregationDefinition = copy(missing = missing.some)
  def script(script: ScriptDefinition): AvgAggregationDefinition = copy(script = script.some)

  override def pipelines(pipelines: Iterable[PipelineAggregationDefinition]): T = copy(pipelines = pipelines.toSeq)
  override def subAggregations(aggs: Iterable[AggregationDefinition]): T = copy(subaggs = aggs.toSeq)
  override def metadata(map: Map[String, AnyRef]): T = copy(metadata = metadata)
}
