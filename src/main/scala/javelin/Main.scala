/*
 * Copyright (C) 2013 Ivan Cukic <ivan at mi.sanu.ac.rs>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package javelin

import org.foment.utils.Filesystem._
import javelin.scripts._

object Main {
    lazy val logger = org.slf4j.LoggerFactory getLogger getClass

    def main(args: Array[String]): Unit = {
        args.toList.tails.foreach {
            case key :: tail =>
                key match {
                    case "--ontology-to-scala" =>
                        OntologyToScala.compile(tail(0), tail(1))

                    case "--show-ontology" =>
                        OntologyOutput.print(tail(0), tail.lift(1))

                    case _ => // nothing
                }

            case _ => // nothing
        }
    }
}
