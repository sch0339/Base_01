package tech.thdev.kotlin_udemy_sample.data

/**
 * Created by tae-hwan on 10/29/16.
 */

data class SampleItem(val message: String,
                      val viewType: Int,
                      var isSelected: Boolean = false)