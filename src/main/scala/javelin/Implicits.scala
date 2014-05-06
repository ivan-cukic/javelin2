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

import java.net.URL

import com.hp.hpl.jena.rdf.model.{ Property, Resource, Literal, ModelFactory }


/**
 * Syntax classes
 */
object Implicits {

    /**
     * The following classes allow the user to do something like this:
     * {{{
     *     model.createResource(someType) ++= Seq(
     *         RDF.`type`   % someType,
     *         RDFS.label   % someResource,
     *         RDFS.comment % someValue
     *     )
     * }}}
     */
    case class PropertyValue[T](property: Property, value: T)

    implicit class SugarResource(resource: Resource) {
        def += (pv: PropertyValue[_]) = {
            pv.value match {
                case value: URL =>
                    resource.addProperty(pv.property, value.toString)

                case value: Resource =>
                    resource.addProperty(pv.property, value)

                case value: Literal =>
                    resource.addProperty(pv.property, value)

                case _ =>
                    resource.addLiteral(pv.property, pv.value)
            }

            resource
        }

        def ++= (properties: Iterable[PropertyValue[_]]) = {
            properties.foreach( resource += _ )

            resource
        }
    }

    implicit class SugarProperty(property: Property) {
        def %[T] (value: T) = new PropertyValue(property, value)
    }

    private
    lazy val model = ModelFactory.createDefaultModel

    implicit
    class StringToResource(ns: String) {
        def URI(f: String) = ns + f

        def ##(f: String) = model createResource URI(f)

        def #>(f: String) = model createProperty URI(f)
    }
}
