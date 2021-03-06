/*
 * Copyright 2019 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.sample.dungeon.board

import kotlin.streams.asSequence

data class BoardCell(val codePoint: Int) {
  constructor(emoji: String) : this(emoji.codePointAt(0))

  init {
    require(Character.isValidCodePoint(codePoint))
  }

  private val string = String(Character.toChars(codePoint))

  val isEmpty get() = this == EMPTY_FLOOR
  val isWall get() = this in WALL_CELLS
  val isToxic get() = this in TOXIC

  override fun toString(): String = string

  companion object {
    val EMPTY_FLOOR = BoardCell(" ")
    val WALL_CELLS = "🌳🧱".asBoardCells()
    val TOXIC = "🔥🌊".asBoardCells()
  }
}

fun String.asBoardCells(): List<BoardCell> = codePoints()
    .asSequence()
    .map(::BoardCell)
    .toList()
