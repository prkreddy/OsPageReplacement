package org.iiitb.mt2013.os.bean;

import org.iiitb.model.bean.InvalidMemoryUnitException;
import org.iiitb.model.bean.Memory;
import org.iiitb.model.bean.MemoryUnit;

/**
 * Simulation of memory management in contiguous, paging and segmentation
 * schemes all require an abstraction of the main memory.
 * <p>
 * This class models the above mentioned abstraction. It can be logically seen
 * as being composed of several <b>memory partitions</b>({@link MemoryUnit} ).
 */
public class MemoryTemp extends Memory<Frame> {
	public MemoryTemp(long size) {
		super(size);
		// TODO Auto-generated constructor stub
	}

	public long getSize() {
		return size;
	}

	/**
	 * Creates a memory model of the specified size.
	 * 
	 * @param size
	 *            The specified size of the memory model.
	 */

	/**
	 * Adds memory units({@link MemoryUnit}) to this memory model.
	 * 
	 * @param mu
	 *            The memory unit to be added
	 * @throws InvalidMemoryUnitException
	 *             Thrown if {@code mu} overlaps any of the existing memory
	 *             units in the memory model.
	 */
	public void add(Frame mu) throws InvalidMemoryUnitException {
		System.out.println("MemoryTemp");
		st.put(mu.getAddress(), mu);
	}

}