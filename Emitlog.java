import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Emitlog
{
	//private final static String QUEUE_NAME="hello"; Going to use Xchange
	private final static String Exchange= "logs";
	
	public static void main(String...args) throws Exception
	{
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		
		Connection connection= connectionFactory.newConnection();
		
		Channel channel=connection.createChannel();
		
		//channel.queueDeclare(QUEUE_NAME,false,false,false,null);
		channel.exchangeDeclare(Exchange,"fanout");
		String message = getMessage(args);
		channel.basicPublish(Exchange,"",null,message.getBytes("UTF-8"));
		System.out.println("[x] '"+message+"' Sent");
		
		channel.close();
		connection.close();
	}
	
	private static String getMessage(String...args)
	{
		if(args.length < 1)
			return "Hello World To Field Of RabbitMQ";
		else
			return joinString(args," ");
	}
	
	private static String joinString(String[] args,String delimiter)
	{
		if(args.length==0)return "Hello";
		StringBuilder word=new StringBuilder(args[0]);
		for(int i=1;i<args.length;i++)
			word.append(delimiter).append(args[i]);
		return word.toString();
	}
}