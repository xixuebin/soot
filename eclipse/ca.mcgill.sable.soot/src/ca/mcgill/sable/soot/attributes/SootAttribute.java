/* Soot - a J*va Optimization Framework
 * Copyright (C) 2003 Jennifer Lhotak
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

package ca.mcgill.sable.soot.attributes;

import java.util.*;

import org.eclipse.swt.graphics.RGB;


public class SootAttribute {

	private int javaEndLn;
	private int javaStartLn;
	private int jimpleEndLn;
	private int jimpleStartLn;
	private int jimpleOffsetStart;
	private int jimpleOffsetEnd;
    private int javaOffsetStart;
    private int javaOffsetEnd;
	private int colorKey;
	private String text;
	private ArrayList textList;
	private ArrayList valueAttrs;
	private String filename;
	private int red;
	private int green;
	private int blue;
	private int fg;
	private ArrayList linkList;

	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append("Java Line: "+javaEndLn+"\n");
		sb.append("Jimple Line: "+jimpleEndLn+"\n");
		sb.append("Jimple Offset Start: "+jimpleOffsetStart+"\n");
		sb.append("Jimple Offset End: "+jimpleOffsetEnd+"\n");
		sb.append("Texts: \n");
		sb.append(getAllTextAttrs("\n"));
		if (getLinkList() != null){
			Iterator it = getLinkList().iterator();
			while(it.hasNext()){
				sb.append("link: "+((LinkAttribute)it.next()).getLabel());
			}
		}
		
		return sb.toString();
	}
	
	private static final String NEWLINE = "\n";
		
	public void addValueAttr(PosColAttribute valAttr){
		if (getValueAttrs() == null){
			setValueAttrs(new ArrayList());
		}
		getValueAttrs().add(valAttr);
	}
	
	public void addLinkAttr(LinkAttribute link){
		if (getLinkList() == null){
			setLinkList(new ArrayList());
		}
		getLinkList().add(link);
		System.out.println("added link for line: "+getJavaStartLn()+" "+link.getLabel());
		addTextAttr(link.getLabel());
	}
	
	public ArrayList getAllLinkAttrs(){
		return getLinkList();
	}

	public void addTextAttr(String text){
		if (getTextList() == null){
			setTextList(new ArrayList());
		}
		getTextList().add(text);
	}
	
	public StringBuffer getAllTextAttrs(String lineSep){
		StringBuffer sb = new StringBuffer();
		if (getTextList() != null){
			Iterator it = getTextList().iterator();
			while (it.hasNext()){
				String next = (String)it.next();
				if (lineSep.equals("<br>")){
					// implies java tooltip
					next = convertHTMLTags(next);
				}
				sb.append(next);
				sb.append(lineSep);
			}
		}
		return sb;
	}
	
	public String convertHTMLTags(String next){
		if (next == null) return null;
		else {
			//System.out.println("next before replace: "+next);
			next = next.replaceAll("<", "&lt;");
			next = next.replaceAll(">", "&gt;");
			//System.out.println("next after replace: "+next);
			return next;
		}
	}
	
	public RGB getRGBColor(){

		//System.out.println("RGB Color: "+getRed()+" "+getGreen()+" "+getBlue());
		return new RGB(getRed(), getGreen(), getBlue());
	}

	// these two are maybe not accurate maybe
	// need to check if ln in question is between
	// the start and end ln's
	public boolean attrForJimpleLn(int jimple_ln) {
		if (getJimpleStartLn() == jimple_ln) return true;
		else return false;
	}
	
	public boolean attrForJavaLn(int java_ln) {
		System.out.println("java start line: "+getJavaStartLn()+" java_ln: "+java_ln);
		if (getJavaStartLn() == java_ln) return true;
		else return false;
	}
	
	public SootAttribute() {
	}
	
	/**
	 * Returns the filename.
	 * @return String
	 */
	public String getFilename() {
		return filename;
	}


	/**
	 * Returns the text.
	 * @return String
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the filename.
	 * @param filename The filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}




	/**
	 * Sets the text.
	 * @param text The text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return
	 */
	public int getColorKey() {
		return colorKey;
	}

	/**
	 * @return
	 */
	public int getJimpleOffsetEnd() {
		return jimpleOffsetEnd;
	}

	/**
	 * @return
	 */
	public int getJimpleOffsetStart() {
		return jimpleOffsetStart;
	}

	/**
	 * @param i
	 */
	public void setColorKey(int i) {
		colorKey = i;
	}

	/**
	 * @param i
	 */
	public void setJimpleOffsetEnd(int i) {
		jimpleOffsetEnd = i;
	}

	/**
	 * @param i
	 */
	public void setJimpleOffsetStart(int i) {
		jimpleOffsetStart = i;
	}

	/**
	 * @return
	 */
	public ArrayList getTextList() {
		return textList;
	}

	/**
	 * @param list
	 */
	public void setTextList(ArrayList list) {
		textList = list;
	}

	/**
	 * @return
	 */
	public ArrayList getValueAttrs() {
		return valueAttrs;
	}

	/**
	 * @param list
	 */
	public void setValueAttrs(ArrayList list) {
		valueAttrs = list;
	}

	/**
	 * @return
	 */
	public int getBlue() {
		return blue;
	}

	/**
	 * @return
	 */
	public int getGreen() {
		return green;
	}



	/**
	 * @return
	 */
	public int getRed() {
		return red;
	}

	/**
	 * @param i
	 */
	public void setBlue(int i) {
		blue = i;
	}

	/**
	 * @param i
	 */
	public void setGreen(int i) {
		green = i;
	}

	

	/**
	 * @param i
	 */
	public void setRed(int i) {
		red = i;
	}

	/**
	 * @return
	 */
	public ArrayList getLinkList() {
		return linkList;
	}

	/**
	 * @param list
	 */
	public void setLinkList(ArrayList list) {
		linkList = list;
	}

    /**
     * @return
     */
    public int getJavaOffsetEnd() {
        return javaOffsetEnd;
    }

    /**
     * @return
     */
    public int getJavaOffsetStart() {
        return javaOffsetStart;
    }

    /**
     * @param i
     */
    public void setJavaOffsetEnd(int i) {
        javaOffsetEnd = i;
    }

    /**
     * @param i
     */
    public void setJavaOffsetStart(int i) {
        javaOffsetStart = i;
    }

	/**
	 * @return
	 */
	public int getFg() {
		return fg;
	}

	/**
	 * @param b
	 */
	public void setFg(int b) {
		fg = b;
	}

	/**
	 * @return
	 */
	public int getJavaEndLn() {
		return javaEndLn;
	}

	/**
	 * @return
	 */
	public int getJavaStartLn() {
		return javaStartLn;
	}

	/**
	 * @return
	 */
	public int getJimpleEndLn() {
		return jimpleEndLn;
	}

	/**
	 * @return
	 */
	public int getJimpleStartLn() {
		return jimpleStartLn;
	}

	/**
	 * @param i
	 */
	public void setJavaEndLn(int i) {
		javaEndLn = i;
	}

	/**
	 * @param i
	 */
	public void setJavaStartLn(int i) {
		javaStartLn = i;
	}

	/**
	 * @param i
	 */
	public void setJimpleEndLn(int i) {
		jimpleEndLn = i;
	}

	/**
	 * @param i
	 */
	public void setJimpleStartLn(int i) {
		jimpleStartLn = i;
	}

}
