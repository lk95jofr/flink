/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.flink.streaming.fsql

/**
 * This is used for executing Table API operations. We use manually generated
 * TypeInfo to check the field types and create serializers and comparators.
 */
class Row(arity: Int) extends Product {

  private val fields = new Array[Any](arity)

  def productArity = fields.length

  def productElement(i: Int): Any = fields(i)

  def setField(i: Int, value: Any): Unit = fields(i) = value

  def canEqual(that: Any) = false

  override def toString = fields.mkString(",")
}

object Row {
  def apply (arr : Array[Any]) : Row= {
    val row = new Row(arr.length)
    for (i <- 0 to arr.length-1){
      row.setField(i, arr(i))
    }
    row
  }

/*
  def apply(p: Product) : Row = {
    val arr = p.productIterator.toArray
    apply(arr)
  }
*/

/*  def apply(value: Any) : Row = {
    val row = new Row(1)
    row.setField(0,value)
    row
  }*/

}


