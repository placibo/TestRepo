import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Emitlog1 {
	private static final String EXCHANGE_NAME = "direct_logs";
	public static void main(String[] argv) throws Exception{
		
	ConnectionFactory factory = new ConnectionFactory();
	factory.setHost("localhost");
	Connection connection = factory.newConnection();
	Channel channel = connection.createChannel();
	
	channel.exchangeDeclare(EXCHANGE_NAME, "direct");
	
	String severity = argv[0];
	String message = argv[1];
	
	channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
	
	System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
	
	channel.close();
	connection.close();
	}
}