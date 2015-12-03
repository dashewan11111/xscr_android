package com.adult.android.utils;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.adult.android.entity.DivisionCity;
import com.adult.android.entity.DivisionProvince;
import com.adult.android.entity.DivisionRegion;

/**
 * @ClassName: XmlParserHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 潘学坤
 * @date 2015年3月6日 下午5:24:32
 * 
 */
public class XmlParserProvienceHandler extends DefaultHandler {
	/**
	 * 存储所有的解析对象
	 */
	private List<DivisionProvince> provinceList = new ArrayList<DivisionProvince>();

	public XmlParserProvienceHandler() {
		super();
	}

	public List<DivisionProvince> getDataList() {
		return provinceList;
	}

	@Override
	public void startDocument() throws SAXException {
		// 当读到第一个开始标签的时候，会触发这个方法
	}

	DivisionProvince divisionProvince = new DivisionProvince();
	DivisionCity divisionCity = new DivisionCity();
	DivisionRegion divisionRegion = new DivisionRegion();

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// 当遇到开始标记的时候，调用这个方法
		if (qName.equals("province")) {
			divisionProvince = new DivisionProvince();
			divisionProvince.setId(attributes.getValue("id"));
			divisionProvince.setName(attributes.getValue("name"));
			divisionProvince.setCity_list(new ArrayList<DivisionCity>());
		} else if (qName.equals("city")) {
			divisionCity = new DivisionCity();
			divisionCity.setId(attributes.getValue("id"));
			divisionCity.setName(attributes.getValue("name"));
			divisionCity.setArea_list(new ArrayList<DivisionRegion>());
		} else if (qName.equals("area")) {
			divisionRegion = new DivisionRegion();
			divisionRegion.setId(attributes.getValue("id"));
			divisionRegion.setName(attributes.getValue("name"));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// 遇到结束标记的时候，会调用这个方法
		if (qName.equals("area")) {
			divisionCity.getArea_list().add(divisionRegion);
		} else if (qName.equals("city")) {
			divisionProvince.getCity_list().add(divisionCity);
		} else if (qName.equals("province")) {
			provinceList.add(divisionProvince);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

}
