package test_webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class testsocket {
	// ����������
	public static void main(String[] args) throws IOException {
		testsocket socketService = new testsocket();
		// 1��a)����һ����������Socket����SocketService
		socketService.oneServer();
	}

	public void oneServer() {
		try {
			ServerSocket server = null;
			try {
				server = new ServerSocket(5209);
				// b)ָ���󶨵Ķ˿ڣ��������˶˿ڡ�
				System.out.println("�����������ɹ�");
				// ����һ��ServerSocket�ڶ˿�5209�����ͻ�����
			} catch (Exception e) {
				System.out.println("û������������" + e);
				// ������ӡ������Ϣ
			}

			while (true) {
				Socket socket = null;
				try {
					socket = server.accept(); // ���̻߳�ȡ�ͻ�������
					Thread workThread = new Thread(new Handler(socket)); // �����߳�
					workThread.start(); // �����߳�
				} catch (Exception e) {
					e.printStackTrace();
				}

				// try{
				// socket=server.accept();
				// //2������accept()������ʼ�������ȴ��ͻ��˵�����
				// //ʹ��accept()�����ȴ��ͻ������пͻ�
				// //�����������һ��Socket���󣬲�����ִ��
				// }catch(Exception e) {
				// System.out.println("Error."+e);
				// //������ӡ������Ϣ
				// }
				// BufferedReader in=new BufferedReader(new
				// InputStreamReader(socket.getInputStream()));
				// //��Socket����õ�����������������Ӧ��BufferedReader����
				// // InputStreamReader in=new
				// InputStreamReader(socket.getInputStream());
				//
				// PrintWriter writer=new PrintWriter(socket.getOutputStream());
				// //��Socket����õ��������������PrintWriter����
				//// int c = 0;
				//// StringBuffer temp = new StringBuffer();
				//// while((c = in.read())!= -1){
				////
				//// temp.append((char)c);
				//// System.out.println(c);
				//// System.out.println(temp.toString());
				////
				//// }
				////// System.out.println(temp.toString());
				//// String s=in.readLine();
				// int c = 0;
				// int a =0;
				// StringBuffer temp = new StringBuffer();
				// while(c!=35||a!=35){//35���ַ�#����˼ ���������##��β
				// a=c;
				// c = in.read();
				// System.out.println(c);
				// temp.append((char)c);
				// }
				// writer.println("���Ǵ����������㴫����ǣ�"+temp);
				//// //��ͻ���������ַ���
				// writer.flush();
				// writer.close(); //�ر�Socket�����
				// in.close(); //�ر�Socket������
				// socket.close(); //�ر�Socket

			}

			// //3����ȡ������������ȡ�ͻ�����Ϣ
			// String line;
			// BufferedReader in=new BufferedReader(new
			// InputStreamReader(socket.getInputStream()));
			// //��Socket����õ�����������������Ӧ��BufferedReader����
			// PrintWriter writer=new PrintWriter(socket.getOutputStream());
			// //��Socket����õ��������������PrintWriter����
			// BufferedReader br=new BufferedReader(new
			// InputStreamReader(System.in));
			// //��ϵͳ��׼�����豸����BufferedReader����
			// System.out.println("Client:"+in.readLine());
			// //�ڱ�׼����ϴ�ӡ�ӿͻ��˶�����ַ���
			// //line=br.readLine();//����̨���뷵����Ϣ
			// //System.out.println("Client111:"+line);
			// //�ӱ�׼�������һ�ַ���
			// //4����ȡ���������Ӧ�ͻ��˵�����
			//// while(!line.equals("end")){
			//// //������ַ���Ϊ "bye"����ֹͣѭ��
			//// writer.println(line);
			//// //��ͻ���������ַ���
			//// writer.flush();
			//// //ˢ���������ʹClient�����յ����ַ���
			//// System.out.println("Server:"+line);
			//// //��ϵͳ��׼����ϴ�ӡ������ַ���
			//// System.out.println("Client144:"+in.readLine());
			//// //��Client����һ�ַ���������ӡ����׼�����
			//// line=br.readLine();
			//// //��ϵͳ��׼�������һ�ַ���
			//// } //����ѭ��
			//
			// while(true){
			// writer.println("���Ƿ�����Ϣ��"+in.readLine());
			// //��ͻ���������ַ���
			// writer.flush();
			// //ˢ���������ʹClient�����յ����ַ���
			// }

			// server.close(); //�ر�ServerSocket
		} catch (Exception e) {// ������ӡ������Ϣ
			System.out.println("Error." + e);
		}
	}

	class Handler implements Runnable {
		private Socket socket;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				System.out.println("������:" + socket.getInetAddress() + ":" + socket.getPort());
				// Thread.sleep(10000);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// ��Socket����õ�����������������Ӧ��BufferedReader����
				// InputStreamReader in=new
				// InputStreamReader(socket.getInputStream());

				PrintWriter writer = new PrintWriter(socket.getOutputStream());
				// ��Socket����õ��������������PrintWriter����
				int c = 0;
				int a = 0;
				StringBuffer temp = new StringBuffer();
				while ((c != 35 || a != 35) && c != -1&& (c != 10 || a != 13)) {// 35���ַ�#����˼ ���������##��β
															// -1�Ƕ�ȡ���ַ�Ϊ��ʱ   13��10�ǻس�
					a = c;
					c = in.read();
					System.out.println(c);
					temp.append((char) c);//ƴ�Ӵ������
				}
				/**
				 * �������д�����ҵ���߼�
				 */
				writer.println("���Ǵ����������㴫����ǣ�" + temp);
				// //��ͻ���������ַ���
				writer.flush();// ˢ���������ʹClient�����յ����ַ���
				writer.close(); // �ر�Socket�����
				in.close(); // �ر�Socket������
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					System.out.println("�ر�����:" + socket.getInetAddress() + ":" + socket.getPort());
					if (socket != null)
						socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
