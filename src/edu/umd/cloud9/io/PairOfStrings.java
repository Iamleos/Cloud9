/*
 * Cloud9: A MapReduce Library for Hadoop
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package edu.umd.cloud9.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

/**
 * <p>
 * Serializable object that represents a pair of string. The elements in the
 * pair are referred to as the left and right elements. This class implements
 * {@link WritableComparable}, and hence can be used as keys to MapReduce jobs.
 * The natural sort order is first by the left element, and then by the right
 * element.
 * </p>
 */
public class PairOfStrings implements WritableComparable {

	private String leftElement, rightElement;

	/**
	 * Creates a pair.
	 */
	public PairOfStrings() {
	}

	/**
	 * Creates a pair.
	 * 
	 * @param left
	 *            the left element
	 * @param right
	 *            the right element
	 */
	public PairOfStrings(String left, String right) {
		set(left, right);
	}

	/**
	 * Deserializes the Tuple.
	 * 
	 * @param in
	 *            source for raw byte representation
	 */
	public void readFields(DataInput in) throws IOException {
		leftElement = in.readUTF();
		rightElement = in.readUTF();
	}

	/**
	 * Serializes this pair.
	 * 
	 * @param out
	 *            where to write the raw byte representation
	 */
	public void write(DataOutput out) throws IOException {
		out.writeUTF(leftElement);
		out.writeUTF(rightElement);
	}

	/**
	 * Returns the left element.
	 * 
	 * @return the left element
	 */
	public String getLeftElement() {
		return leftElement;
	}

	/**
	 * Returns the right element.
	 * 
	 * @return the right element
	 */
	public String getRightElement() {
		return rightElement;
	}

	/**
	 * Sets the right and left elements of this pair.
	 * 
	 * @param left
	 *            the left element
	 * @param right
	 *            the right element
	 */
	public void set(String left, String right) {
		leftElement = left;
		rightElement = right;
	}

	/**
	 * Checks two pairs for equality.
	 * 
	 * @param obj
	 *            object for comparison
	 * @return <code>true</code> if <code>obj</code> is equal to this
	 *         object, <code>false</code> otherwise
	 */
	public boolean equals(Object obj) {
		PairOfStrings pair = (PairOfStrings) obj;
		return leftElement.equals(pair.getLeftElement())
				&& rightElement.equals(pair.getRightElement());
	}

	/**
	 * Defines a natural sort order for pairs. Pairs are sorted first by the
	 * left element, and then by the right element.
	 * 
	 * @return a value less than zero, a value greater than zero, or zero if
	 *         this Tuple should be sorted before, sorted after, or is equal to
	 *         <code>obj</code>.
	 */
	public int compareTo(Object obj) {
		PairOfStrings pair = (PairOfStrings) obj;

		String pl = pair.getLeftElement();
		String pr = pair.getRightElement();

		if (leftElement.equals(pl)) {
			return rightElement.compareTo(pr);
		}

		return leftElement.compareTo(pl);
	}

	/**
	 * Returns a hash code value for the pair.
	 * 
	 * @return hash code for the pair
	 */
	public int hashCode() {
		return leftElement.hashCode() + rightElement.hashCode();
	}

	/**
	 * Generates human-readable String representation of this pair.
	 * 
	 * @return human-readable String representation of this pair
	 */
	public String toString() {
		return "(" + leftElement + ", " + rightElement + ")";
	}

}
