package ser;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

import avro.domain.User;

/**
 * 学习如何使用Avro对象,并实现序列化和反序列化
 * @author tedu
 *
 */
public class TestDemo {
	
	@Test
	public void create(){
		User u1=new User();
		u1.setUsername("tom");
		u1.setAge(23);
		
		User u2=new User("rose",23);
		
		System.out.println(u1);
		System.out.println(u2);
	}
	@Test
	public void write() throws Exception{
		DatumWriter<User> dw=new SpecificDatumWriter<User>(User.class);
		//--此对象用于将数据序列化到文件中
		DataFileWriter<User> dfw=new DataFileWriter<User>(dw);
		
		//--①参:指定序列化类的模式
		//--②参:输出的文件路径
		dfw.create(User.SCHEMA$,new File("1.txt"));
		
		User u1=new User("tom",23);
	    User u2=new User("rose",25);
	    
	    //--执行序列化
	    dfw.append(u1);
	    dfw.append(u2);
	    
	    //--avro序列化的优势在于可以做到对象结构复用
	    //--极大的节省序列化之后的数据存储空间
	    //--由于在海量数据时,优势明显
	    dfw.close();
	    
	}
	@Test
	public void read() throws Exception{
		DatumReader<User> dr=new SpecificDatumReader<User>(User.class);
		//--此对象用于从文件里反序列化数据
		DataFileReader<User> dfr=
				new DataFileReader<User>(new File("1.txt"), dr);
		//--获取反序列之后的对象的迭代器
		Iterator<User> it=dfr.iterator();
		while(it.hasNext()){
			User u=it.next();
			System.out.println(u);
		}
	}
}
