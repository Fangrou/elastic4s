package com.sksamuel.elastic4s.searches.aggs

import com.sksamuel.elastic4s.script.ScriptDefinition
import com.sksamuel.elastic4s.searches.aggs.pipeline.PipelineAggregationDefinition
import com.sksamuel.exts.OptionImplicits._
import org.elasticsearch.search.aggregations.Aggregator
import org.elasticsearch.search.aggregations.bucket.terms.Terms
import org.elasticsearch.search.aggregations.bucket.terms.support.IncludeExclude
import org.elasticsearch.search.aggregations.support.ValueType

case class TermsAggregationDefinition(name: String,
                                      field: Option[String] = None,
                                      script: Option[ScriptDefinition] = None,
                                      missing: Option[String] = None,
                                      size: Option[Int] = None,
                                      minDocCount: Option[Long] = None,
                                      showTermDocCountError: Option[Boolean] = None,
                                      valueType: Option[ValueType] = None,
                                      executionHint: Option[String] = None,
                                      shardMinDocCount: Option[Long] = None,
                                      collectMode: Option[Aggregator.SubAggCollectionMode] = None,
                                      order: Option[Terms.Order] = None,
                                      shardSize: Option[Int] = None,
                                      includeExclude: Option[IncludeExclude] = None,
                                      pipelines: Seq[PipelineAggregationDefinition] = Nil,
                                      subaggs: Seq[AggregationDefinition] = Nil,
                                      metadata: Map[String, AnyRef] = Map.empty)
  extends AggregationDefinition {

  type T = TermsAggregationDefinition

  def field(field: String): TermsAggregationDefinition = copy(field = field.some)
  def script(script: ScriptDefinition): TermsAggregationDefinition = copy(script = script.some)
  def missing(missing: String): TermsAggregationDefinition = copy(missing = missing.some)
  def size(size: Int): TermsAggregationDefinition = copy(size = size.some)
  def minDocCount(min: Long): TermsAggregationDefinition = copy(minDocCount = min.some)
  def showTermDocCountError(showError: Boolean): TermsAggregationDefinition = copy(showTermDocCountError = showError.some)
  def valueType(valueType: ValueType): TermsAggregationDefinition = copy(valueType = valueType.some)
  def executionHint(hint: String): TermsAggregationDefinition = copy(executionHint = hint.some)
  def shardMinDocCount(min: Long): TermsAggregationDefinition = copy(shardMinDocCount = min.some)
  def collectMode(mode: Aggregator.SubAggCollectionMode): TermsAggregationDefinition = copy(collectMode = mode.some)
  def order(order: Terms.Order): TermsAggregationDefinition = copy(order = order.some)
  def shardSize(shardSize: Int): TermsAggregationDefinition = copy(shardSize = shardSize.some)

  def includeExclude(include: String, exclude: String): TermsAggregationDefinition =
    copy(includeExclude = new IncludeExclude(include.some.orNull, exclude.some.orNull).some)

  def includeExclude(include: Iterable[String], exclude: Iterable[String]): TermsAggregationDefinition = {
    // empty array doesn't work, has to be null
    val inc = if (include.isEmpty) null else include.toArray
    val exc = if (exclude.isEmpty) null else exclude.toArray
    copy(includeExclude = new IncludeExclude(inc, exc).some)
  }

  def includeExcludeLongs(include: Iterable[Long], exclude: Iterable[Long]): TermsAggregationDefinition = {
    // empty array doesn't work, has to be null
    val inc = if (include.isEmpty) null else include.toArray
    val exc = if (exclude.isEmpty) null else exclude.toArray
    copy(includeExclude = new IncludeExclude(inc, exc).some)
  }

  def includeExcludeDoubles(include: Iterable[Double], exclude: Iterable[Double]): TermsAggregationDefinition = {
    // empty array doesn't work, has to be null
    val inc = if (include.isEmpty) null else include.toArray
    val exc = if (exclude.isEmpty) null else exclude.toArray
    copy(includeExclude = new IncludeExclude(inc, exc).some)
  }

  override def pipelines(pipelines: Iterable[PipelineAggregationDefinition]): T = copy(pipelines = pipelines.toSeq)
  override def subAggregations(aggs: Iterable[AggregationDefinition]): T = copy(subaggs = aggs.toSeq)
  override def metadata(map: Map[String, AnyRef]): T = copy(metadata = metadata)
}
