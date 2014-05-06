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

package javelin.scripts

import java.io.File
import scala.collection.JavaConversions._
import scala.collection.mutable.Buffer

import com.hp.hpl.jena.rdf.model.{ Property, Statement }
import com.hp.hpl.jena.vocabulary.{ RDF, RDFS }

import org.foment.utils.Exceptions._
import javelin.OntologyLoader

object OntologyOutput {

    lazy val logger = org.slf4j.LoggerFactory getLogger getClass

    def print(configFile: String, format: Option[String]): Unit = {
        print(new File(configFile), format)
    }

    def print(configFile: File, format: Option[String]): Unit =
        OntologyLoader.loadOntology(configFile).foreach { ontology =>
            // Reading the ontology properties
            ontology.model.write(System.out, format getOrElse "TURTLE")
        }

}
