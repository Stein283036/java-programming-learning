package com.guhe.xmljson;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author njl
 * @date 2023/2/7
 */
public class XmlApp {
	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
//		x1();
		x2();
	}

	/**
	 * 使用 jackson 解析 xml 文件
	 */
	public static void x2() throws IOException {
		JacksonXmlModule module = new JacksonXmlModule();
		XmlMapper mapper = new XmlMapper(module);
		Book book = mapper.readValue(new File("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\resources\\book.xml"), Book.class);
		System.out.println("book = " + book);
	}

	/**
	 * 使用 JDK 自带的解析 xml 文件的类
	 */
	public static void x1() throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
		Document document = documentBuilder.parse(new File("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\resources\\book.xml"));
		System.out.println("document = " + document);
		Element element = document.getDocumentElement();
		String id = element.getAttribute("id");
		String nodeName = element.getNodeName();
		System.out.println("nodeName = " + nodeName);
		System.out.println("id = " + id);
		System.out.println("element = " + element);
		NodeList childNodes = element.getChildNodes();
	}
}

class Book {
	Long id;
	String name;
	String author;
	String isbn;
	List<String> tags;
	Date pubDate;

	public Book() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", name='" + name + '\'' +
				", author='" + author + '\'' +
				", isbn='" + isbn + '\'' +
				", tags=" + tags +
				", pubDate=" + pubDate +
				'}';
	}
}