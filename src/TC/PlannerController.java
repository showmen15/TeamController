package TC;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.UnknownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class PlannerController 
{
	private String URL; 

	private HttpClient client;
	private HttpPost post;
	/*
	public PlannerController(String sURL)
	{
		URL = sURL;

		client = new DefaultHttpClient();
		post = new HttpPost(sURL);
	}*/

	TCPClient tcpClient;

	public PlannerController(String sIP,int port) throws UnknownHostException, IOException
	{	
		tcpClient = new TCPClient(sIP, port);
	}

	public String GetPlan(String sJSON,int iPlanNr) throws ClientProtocolException, IOException, InterruptedException
	{
		//logika dla TCP awaryjna
		String tmpPlan;

		if(iPlanNr == 1)
			return "[['goto 2.5 4.08 Node172', 'search Space171', 'goto 2.10046423835 4.27981430466 Node173', 'goto 1.89958397485 4.2797226499 Node163', 'goto 1.7 4.08 Node162', 'goto 1.50041602515 3.8802773501 Node164', 'goto 1.29951923803 3.88013736056 Node209', 'goto 0.7 4.08 Node207', 'search Space206', 'goto 0.550089970495 3.3604918387 Node208', 'goto 0.550427231222 3.15974024342 Node222', 'goto 0.61041489197 2.60027904239 Node224'],['goto 0.46 1.94 Node183', 'goto 0.275156841626 1.48047476384 Node185', 'goto 0.275068197237 1.2795046727 Node235', 'goto 0.37 0.69 Node234', 'goto 0.63 0.94 Node236', 'goto 0.86 0.94 Node237', 'goto 1.56 0.9 Node238', 'goto 1.65 1.3 Node239', 'goto 1.65 1.49 Node239', 'goto 1.65 1.96 Node240']]"; //przejazd dwoch robotow
		else
			return "[['goto 2.5 4.08 Node172', 'search Space171', 'goto 2.10046423835 4.27981430466 Node173', 'goto 1.89958397485 4.2797226499 Node163', 'goto 1.7 4.08 Node162', 'goto 1.50041602515 3.8802773501 Node164'],['goto 0.609870631957 2.39951702597 Node186', 'goto 0.46 1.94 Node183', 'goto 0.275156841626 1.48047476384 Node185', 'goto 0.275068197237 1.2795046727 Node235', 'goto 0.37 0.69 Node234', 'goto 0.63 0.94 Node236', 'goto 0.86 0.94 Node237', 'goto 1.56 0.9 Node238', 'goto 1.65 1.3 Node239', 'goto 1.65 1.49 Node239', 'goto 1.65 1.96 Node240', 'goto 2.02 2.4 Node777', 'goto 2.02 2.6 Node778', 'goto 1.18 2.88 Node779', 'goto 0.550427231222 3.15974024342 Node222', 'goto 0.550089970495 3.3604918387 Node208', 'goto 0.7 4.08 Node207']]"; //przejazd dwoch robotow

		//'goto 0.609870631957 2.39951702597 Node186',
		//return "[['goto 2.5 4.08 Node172', 'search Space171', 'goto 2.10046423835 4.27981430466 Node173', 'goto 1.89958397485 4.2797226499 Node163', 'goto 1.7 4.08 Node162', 'goto 1.50041602515 3.8802773501 Node164'],['goto 0.609870631957 2.39951702597 Node186', 'goto 0.46 1.94 Node183', 'goto 0.275156841626 1.48047476384 Node185', 'goto 0.275068197237 1.2795046727 Node235', 'goto 0.37 0.69 Node234', 'goto 0.63 0.94 Node236', 'goto 0.86 0.94 Node237', 'goto 1.56 0.9 Node238', 'goto 1.65 1.3 Node239', 'goto 1.65 1.49 Node239', 'goto 1.65 1.96 Node240', 'goto 2.02 2.4 Node777', 'goto 2.02 2.6 Node778', 'goto 1.18 2.88 Node779', 'goto 0.550427231222 3.15974024342 Node222', 'goto 0.550089970495 3.3604918387 Node208', 'goto 0.7 4.08 Node207']]"; //przejazd dwoch robotow

		//			return "[['goto 2.5 4.08 Node172', 'search Space171', 'goto 2.10046423835 4.27981430466 Node173', 'goto 1.89958397485 4.2797226499 Node163', 'goto 1.7 4.08 Node162', 'goto 1.50041602515 3.8802773501 Node164', 'goto 1.29951923803 3.88013736056 Node209', 'goto 0.7 4.08 Node207', 'search Space206', 'goto 0.550089970495 3.3604918387 Node208', 'goto 0.550427231222 3.15974024342 Node222', 'goto 0.61041489197 2.60027904239 Node224'],['goto 0.609870631957 2.39951702597 Node186', 'goto 0.46 1.94 Node183', 'goto 0.275156841626 1.48047476384 Node185', 'goto 0.275068197237 1.2795046727 Node235', 'goto 0.37 0.69 Node234', 'goto 0.63 0.94 Node236', 'goto 0.86 0.94 Node237', 'goto 1.56 0.9 Node238', 'goto 1.65 1.3 Node239', 'goto 1.65 1.49 Node239', 'goto 1.65 1.96 Node240', 'goto 2.02 2.4 Node777', 'goto 2.02 2.6 Node778', 'goto 1.18 2.88 Node779', 'goto 0.550089970495 3.3604918387 Node770']]"; //przejazd dwoch robotow

		//tmpPlan = tcpClient.CommunicateWithPlanner(sJSON); 
		//return tmpPlan;

		//logika dla Rest-a
		//String tempJSONRequest = getRequest(sJSON);
		//String tempJSONResponse = getResponse(tempJSONRequest);
		//String tempJSONPlan = getJSONPlan(tempJSONResponse);		
		//return tempJSONPlan;

		//wyslj plan do planer i pobierz plan;
		//return 	"[['goto 13.9280556742 5.8314117592 Node1', 'goto 15.2255972991 7.11963471749 Node4', 'search Space2', 'goto 16.0514406825 8.9449781059 Node6', 'goto 16.9514257266 11.5398921354 Node7', 'search Space3'],['goto 13.9280556742 5.8314117592 Node1', 'goto 15.2255972991 7.11963471749 Node4', 'search Space2', 'goto 16.0514406825 8.9449781059 Node6', 'goto 16.9514257266 11.5398921354 Node7', 'search Space3']]";
		//	return "[['goto 13.9280556742 5.8314117592 Node591', 'goto 31.9632495189 2.54420830963 Node1450', 'search Space1449', 'goto 31.092934922 4.99452679524 Node1451', 'goto 31.0929681837 5.19546051482 Node707', 'goto 32.48 6.38 Node690', 'goto 43.8842537775 19.4596485026 Node697', 'goto 43.8847738824 19.6604721624 Node1031', 'goto 45.9107554898 25.3753625621 Node1025', 'goto 48.8822639996 28.8447187712 Node1029', 'goto 48.8116391517 29.0326023427 Node1299', 'goto 48.7901424348 32.7946758013 Node1298', 'search Space1297', 'goto 48.8116391517 29.0326023427 Node1299', 'goto 48.8822639996 28.8447187712 Node1029', 'goto 45.9107554898 25.3753625621 Node1025', 'goto 42.4166699433 25.3047796632 Node1026', 'goto 42.2293162258 25.232063122 Node993', 'goto 27.7498502616 20.5328726358 Node981', 'goto 28.4934258921 21.0890440242 Node990', 'goto 28.4226811048 21.2768471394 Node1187', 'goto 27.2854865325 24.2733355165 Node1186', 'search Space1185', 'goto 28.4226811048 21.2768471394 Node1187', 'goto 28.4934258921 21.0890440242 Node990', 'goto 27.7498502616 20.5328726358 Node981', 'goto 16.9513234713 11.7403178462 Node982', 'goto 16.9514257266 11.5398921354 Node692', 'goto 32.48 6.38 Node690', 'goto 20.4868807411 5.19512683147 Node711', 'goto 20.4861331516 4.99457534247 Node1501', 'goto 19.7024625393 3.83372944287 Node1500', 'search Space1499', 'goto 20.4861331516 4.99457534247 Node1501', 'goto 20.4868807411 5.19512683147 Node711', 'goto 32.48 6.38 Node690', 'goto 16.9514257266 11.5398921354 Node692', 'goto 16.9513234713 11.7403178462 Node982', 'goto 27.7498502616 20.5328726358 Node981', 'goto 41.168472011 25.9188007633 Node992', 'goto 41.0977156303 26.1063998566 Node1229', 'goto 39.1555821745 28.8898747589 Node1228', 'search Space1227', 'goto 41.0977156303 26.1063998566 Node1229', 'goto 41.168472011 25.9188007633 Node992', 'goto 27.7498502616 20.5328726358 Node981', 'goto 16.9513234713 11.7403178462 Node982', 'goto 16.9514257266 11.5398921354 Node692', 'goto 32.48 6.38 Node690', 'goto 44.784184667 15.9697361381 Node699', 'goto 44.9850966078 15.9698877315 Node1343', 'goto 48.2546860163 15.1934643757 Node1342', 'search Space1341', 'goto 44.9850966078 15.9698877315 Node1343', 'goto 44.784184667 15.9697361381 Node699', 'goto 32.48 6.38 Node690', 'goto 44.7842058301 17.3447047856 Node698', 'goto 44.9850986675 17.3451029214 Node1328', 'goto 47.9809815444 17.9963137753 Node1327', 'search Space1326', 'goto 44.9850986675 17.3451029214 Node1328', 'goto 44.7842058301 17.3447047856 Node698', 'goto 32.48 6.38 Node690', 'goto 37.59 7.53 Node696', 'goto 37.5889628342 7.77042148098 Node897', 'goto 39.6203034269 10.8533736102 Node896', 'search Space895', 'goto 37.5889628342 7.77042148098 Node897', 'goto 37.59 7.53 Node696', 'goto 32.48 6.38 Node690', 'goto 44.7841218324 11.0598890848 Node700', 'goto 44.9850893435 11.0601401078 Node1356', 'goto 47.8923632246 11.9379947322 Node1355', 'search Space1354', 'goto 44.9850893435 11.0601401078 Node1356', 'goto 44.7841218324 11.0598890848 Node700', 'goto 32.48 6.38 Node690', 'goto 16.9514257266 11.5398921354 Node692', 'goto 16.9513234713 11.7403178462 Node982', 'goto 27.7498502616 20.5328726358 Node981', 'goto 10.9287516908 13.3764948389 Node983', 'goto 10.7434192428 13.2986975044 Node1042', 'goto 5.5645508451 11.5312363707 Node1041', 'search Space1040', 'goto 10.7434192428 13.2986975044 Node1042', 'goto 10.9287516908 13.3764948389 Node983', 'goto 27.7498502616 20.5328726358 Node981', 'goto 17.9124734536 17.0941497356 Node986', 'goto 17.8410481341 17.2814883133 Node1110', 'goto 17.8235848341 18.4583076255 Node1108', 'goto 17.0637510331 19.3305501073 Node1109', 'goto 16.9924734077 19.5184329971 Node1129', 'goto 16.9187100544 21.7380349326 Node1128', 'search Space1127', 'goto 16.9924734077 19.5184329971 Node1129', 'goto 17.0637510331 19.3305501073 Node1109', 'goto 17.8235848341 18.4583076255 Node1108', 'goto 17.8410481341 17.2814883133 Node1110', 'goto 17.9124734536 17.0941497356 Node986', 'goto 27.7498502616 20.5328726358 Node981', 'goto 42.2293162258 25.232063122 Node993', 'goto 42.4166699433 25.3047796632 Node1026', 'goto 45.9107554898 25.3753625621 Node1025', 'goto 45.0347513847 22.0804794093 Node1030', 'goto 45.0351091168 22.079983935 Node1311', 'goto 45.952583548 22.0472755716 Node1310', 'search Space1309', 'goto 45.0351091168 22.079983935 Node1311', 'goto 45.0347513847 22.0804794093 Node1030', 'goto 45.9107554898 25.3753625621 Node1025', 'goto 42.4166699433 25.3047796632 Node1026', 'goto 42.2293162258 25.232063122 Node993', 'goto 27.7498502616 20.5328726358 Node981', 'goto 17.9124734536 17.0941497356 Node986', 'goto 17.8410481341 17.2814883133 Node1110', 'goto 17.8235848341 18.4583076255 Node1108', 'search Space1107', 'goto 17.8410481341 17.2814883133 Node1110', 'goto 17.9124734536 17.0941497356 Node986', 'goto 27.7498502616 20.5328726358 Node981', 'goto 16.9513234713 11.7403178462 Node982', 'goto 16.9514257266 11.5398921354 Node692', 'goto 32.48 6.38 Node690', 'goto 16.0514370363 6.09506409997 Node712', 'goto 15.8504453535 6.09493539461 Node592', 'goto 13.9280556742 5.8314117592 Node591', 'goto 15.2255970134 4.54536501521 Node593', 'goto 15.2261015543 4.34452725778 Node1522', 'goto 16.0295411164 2.11196351201 Node1521', 'search Space1520', 'goto 15.2261015543 4.34452725778 Node1522', 'goto 15.2255970134 4.54536501521 Node593', 'goto 13.9280556742 5.8314117592 Node591', 'goto 15.8504453535 6.09493539461 Node592', 'goto 16.0514370363 6.09506409997 Node712', 'goto 32.48 6.38 Node690', 'goto 43.8842537775 19.4596485026 Node697', 'goto 43.8847738824 19.6604721624 Node1031', 'goto 45.9107554898 25.3753625621 Node1025', 'goto 47.3396407706 28.2594284615 Node1028', 'goto 47.2686335991 28.4472909698 Node1271', 'goto 45.3265004233 31.2307651995 Node1270', 'search Space1269', 'goto 47.2686335991 28.4472909698 Node1271', 'goto 47.3396407706 28.2594284615 Node1028', 'goto 45.9107554898 25.3753625621 Node1025', 'goto 42.4166699433 25.3047796632 Node1026', 'goto 42.2293162258 25.232063122 Node993', 'goto 27.7498502616 20.5328726358 Node981', 'goto 11.8493971063 14.8636596395 Node984', 'goto 11.7778501966 15.0509729444 Node1063', 'goto 10.808307383 18.2485370146 Node1062', 'search Space1061', 'goto 11.7778501966 15.0509729444 Node1063', 'goto 11.8493971063 14.8636596395 Node984', 'goto 27.7498502616 20.5328726358 Node981', 'goto 22.7745124904 18.9383293504 Node988', 'goto 22.7027235209 19.1255490168 Node1160', 'goto 21.9353325633 20.0180630126 Node1158', 'search Space1157', 'goto 22.7027235209 19.1255490168 Node1160', 'goto 22.7745124904 18.9383293504 Node988', 'goto 27.7498502616 20.5328726358 Node981', 'goto 16.9513234713 11.7403178462 Node982', 'goto 16.9514257266 11.5398921354 Node692', 'goto 32.48 6.38 Node690', 'goto 28.0077222153 5.19528936144 Node708', 'goto 28.0074735452 4.99452598554 Node1464', 'goto 28.8634292762 2.54420830963 Node1463', 'search Space1462', 'goto 28.0074735452 4.99452598554 Node1464', 'goto 28.0077222153 5.19528936144 Node708', 'goto 32.48 6.38 Node690', 'goto 43.4341283841 5.19513655683 Node703', 'goto 43.4348229108 4.99454789111 Node1399', 'goto 44.6443885508 2.53359429457 Node1398', 'search Space1397', 'goto 43.4348229108 4.99454789111 Node1399', 'goto 43.4341283841 5.19513655683 Node703', 'goto 32.48 6.38 Node690', 'goto 31.4 7.54 Node695', 'goto 31.4180495658 7.77041683706 Node765', 'goto 33.484263567 10.7895264711 Node763', 'search Space762', 'goto 31.4180495658 7.77041683706 Node765', 'goto 31.4 7.54 Node695', 'goto 32.48 6.38 Node690', 'goto 16.9514257266 11.5398921354 Node692', 'goto 16.9513234713 11.7403178462 Node982', 'goto 27.7498502616 20.5328726358 Node981', 'goto 38.0830131451 24.7483540426 Node991', 'goto 38.0122494422 24.9359525203 Node1208', 'goto 36.0193282253 27.7010018888 Node1207', 'search Space1206', 'goto 38.0122494422 24.9359525203 Node1208', 'goto 38.0830131451 24.7483540426 Node991', 'goto 27.7498502616 20.5328726358 Node981', 'goto 16.9513234713 11.7403178462 Node982', 'goto 16.9514257266 11.5398921354 Node692', 'goto 32.48 6.38 Node690', 'goto 24.922316878 5.19519261741 Node709', 'goto 24.9220169533 4.99452679524 Node1477', 'goto 25.7923315502 2.54420830963 Node1476', 'search Space1475', 'goto 24.9220169533 4.99452679524 Node1477', 'goto 24.922316878 5.19519261741 Node709', 'goto 32.48 6.38 Node690', 'goto 21.8368759607 5.19514178302 Node710', 'goto 21.8365555765 4.99452598554 Node1490', 'goto 22.6925113075 2.54420830963 Node1489', 'search Space1488', 'goto 21.8365555765 4.99452598554 Node1490', 'goto 21.8368759607 5.19514178302 Node710', 'goto 32.48 6.38 Node690', 'goto 44.7841139761 6.52006767522 Node702', 'goto 44.985008515 6.51969885674 Node1386', 'goto 48.4358243383 3.8406783657 Node1385', 'search Space1384', 'goto 44.985008515 6.51969885674 Node1386', 'goto 44.7841139761 6.52006767522 Node702', 'goto 32.48 6.38 Node690', 'goto 16.9514257266 11.5398921354 Node692', 'goto 16.9513234713 11.7403178462 Node982', 'goto 27.7498502616 20.5328726358 Node981', 'goto 26.851048528 20.4606719931 Node989', 'goto 26.7794350358 20.6481483257 Node1180', 'goto 26.5054352166 21.3699302158 Node1179', 'search Space1178', 'goto 26.7794350358 20.6481483257 Node1180', 'goto 26.851048528 20.4606719931 Node989', 'goto 27.7498502616 20.5328726358 Node981', 'goto 14.9111672473 15.9556195372 Node985', 'goto 14.839806225 16.1429503957 Node1081', 'goto 15.0444392159 17.8338987484 Node1079', 'search Space1078', 'goto 14.839806225 16.1429503957 Node1081', 'goto 14.9111672473 15.9556195372 Node985', 'goto 27.7498502616 20.5328726358 Node981', 'goto 22.7745124904 18.9383293504 Node988', 'goto 22.7027235209 19.1255490168 Node1160', 'goto 21.9353325633 20.0180630126 Node1158', 'goto 21.9253848469 21.174774753 Node1159', 'goto 21.8540816553 21.3626300002 Node1173', 'goto 20.2997503511 22.9000388659 Node1172', 'search Space1171', 'goto 21.8540816553 21.3626300002 Node1173', 'goto 21.9253848469 21.174774753 Node1159', 'goto 21.9353325633 20.0180630126 Node1158', 'goto 22.7027235209 19.1255490168 Node1160', 'goto 22.7745124904 18.9383293504 Node988', 'goto 27.7498502616 20.5328726358 Node981', 'goto 16.9513234713 11.7403178462 Node982', 'goto 16.9514257266 11.5398921354 Node692', 'goto 32.48 6.38 Node690', 'goto 34.1779869925 5.19543561959 Node706', 'goto 34.1783915142 4.99452598561 Node1438', 'goto 35.0343484628 2.5442082895 Node1437', 'search Space1436', 'goto 34.1783915142 4.99452598561 Node1438', 'goto 34.1779869925 5.19543561959 Node706', 'goto 32.48 6.38 Node690', 'goto 40.3486876889 5.1951834171 Node704', 'goto 40.3493123218 4.99452611919 Node1412', 'goto 41.2100767032 2.53699651625 Node1411', 'search Space1410', 'goto 40.3493123218 4.99452611919 Node1412', 'goto 40.3486876889 5.1951834171 Node704', 'goto 32.48 6.38 Node690', 'goto 43.8842537775 19.4596485026 Node697', 'goto 43.8847738824 19.6604721624 Node1031', 'goto 45.9107554898 25.3753625621 Node1025', 'goto 44.2547351737 27.0890706412 Node1027', 'goto 44.1831763672 27.2768465479 Node1250', 'goto 42.2535668459 30.0650717502 Node1249', 'search Space1248', 'goto 44.1831763672 27.2768465479 Node1250', 'goto 44.2547351737 27.0890706412 Node1027', 'goto 45.9107554898 25.3753625621 Node1025', 'goto 43.8847738824 19.6604721624 Node1031', 'goto 43.8842537775 19.4596485026 Node697', 'goto 32.48 6.38 Node690', 'goto 37.2632737236 5.19527110112 Node705', 'goto 37.2638553322 4.99452679524 Node1425', 'goto 38.1341699291 2.54420830963 Node1424', 'search Space1423', 'goto 37.2638553322 4.99452679524 Node1425', 'goto 37.2632737236 5.19527110112 Node705', 'goto 32.48 6.38 Node690', 'goto 44.7841095229 7.92001216048 Node701', 'goto 44.985089614 7.920139178 Node1369', 'goto 47.8933832179 8.79197239568 Node1368', 'search Space1367', 'goto 44.985089614 7.920139178 Node1369', 'goto 44.7841095229 7.92001216048 Node701', 'goto 32.48 6.38 Node690', 'goto 16.0514406825 8.9449781059 Node691', 'goto 15.850495295 8.94522627969 Node732', 'goto 14.0053917537 9.93237559512 Node730', 'search Space729', 'goto 15.850495295 8.94522627969 Node732', 'goto 16.0514406825 8.9449781059 Node691', 'goto 32.48 6.38 Node690', 'goto 16.9514257266 11.5398921354 Node692', 'goto 16.9513234713 11.7403178462 Node982', 'goto 27.7498502616 20.5328726358 Node981', 'goto 20.5584968134 18.0978797243 Node987', 'goto 20.486823083 18.2851490021 Node1140', 'goto 19.8916911585 19.2168465052 Node1139', 'search Space1138']]";
		//return 	"[['goto 2.44725 4.22125 Node1', 'goto 1.46706 4.14285 Node4', 'search Space2', 'goto 0.673888 3.76964 Node6', 'goto 0.473391 2.96781 Node7', 'search Space3']]"; //test latwy
		//return 	"[['goto 2.44725 4.22125 Node1', 'goto 1.46706 4.14285 Node4', 'search Space2', 'goto 0.673888 3.76964 Node6', 'goto 0.473391 2.96781 Node7', 'search Space3', 'goto 1.4809 2.89615 Node8', 'goto 2.10877 2.89753 Node9']]"; //test ze skrecaniem

		//return "[['goto 2.5 4.08 Node172', 'search Space171', 'goto 2.10046423835 4.27981430466 Node173', 'goto 1.89958397485 4.2797226499 Node163', 'goto 1.7 4.08 Node162', 'goto 1.50041602515 3.8802773501 Node164', 'goto 1.29951923803 3.88013736056 Node209', 'goto 0.7 4.08 Node207', 'search Space206', 'goto 0.550089970495 3.3604918387 Node208', 'goto 0.550427231222 3.15974024342 Node222', 'goto 0.61041489197 2.60027904239 Node224', 'goto 0.609870631957 2.39951702597 Node186', 'goto 0.46 1.94 Node183', 'goto 0.275156841626 1.48047476384 Node185', 'goto 0.275068197237 1.2795046727 Node235', 'goto 0.37 0.69 Node234', 'goto 0.63 0.94 Node236', 'goto 0.86 0.94 Node237', 'goto 1.56 0.9 Node238', 'goto 1.65 1.3 Node239', 'goto 1.65 1.49 Node239', 'goto 1.65 1.96 Node240']]"; //przejazd pojedynczego robota
		
		//return "[['goto 2.5 4.08 Node172', 'search Space171', 'goto 2.10046423835 4.27981430466 Node173', 'goto 1.89958397485 4.2797226499 Node163', 'goto 1.7 4.08 Node162', 'goto 1.50041602515 3.8802773501 Node164', 'goto 1.29951923803 3.88013736056 Node209', 'goto 0.7 4.08 Node207', 'search Space206', 'goto 0.550089970495 3.3604918387 Node208', 'goto 0.550427231222 3.15974024342 Node222', 'goto 0.61041489197 2.60027904239 Node224', 'goto 0.609870631957 2.39951702597 Node186', 'goto 0.46 1.94 Node183', 'goto 0.275156841626 1.48047476384 Node185', 'goto 0.275068197237 1.2795046727 Node235', 'goto 0.37 0.69 Node234', 'goto 0.63 0.94 Node236', 'goto 0.86 0.94 Node237', 'goto 1.56 0.9 Node238', 'goto 1.65 1.3 Node239', 'goto 1.65 1.49 Node239', 'goto 1.65 1.96 Node240']]"; //przejazd pojedynczego robota
		
		//return "[[]]"; //przejazd pojedynczego robota
		
		//return "[['goto 2.5 4.08 Node172', 'search Space171', 'goto 2.10046423835 4.27981430466 Node173', 'goto 1.89958397485 4.2797226499 Node163', 'goto 1.7 4.08 Node162', 'goto 1.50041602515 3.8802773501 Node164', 'goto 1.29951923803 3.88013736056 Node209', 'goto 0.7 4.08 Node207', 'search Space206', 'goto 0.550089970495 3.3604918387 Node208', 'goto 0.550427231222 3.15974024342 Node222', 'goto 0.61041489197 2.60027904239 Node224'],['goto 0.609870631957 2.39951702597 Node186', 'goto 0.46 1.94 Node183', 'goto 0.275156841626 1.48047476384 Node185', 'goto 0.275068197237 1.2795046727 Node235', 'goto 0.37 0.69 Node234', 'goto 0.63 0.94 Node236', 'goto 0.86 0.94 Node237', 'goto 1.56 0.9 Node238', 'goto 1.65 1.3 Node239', 'goto 1.65 1.49 Node239', 'goto 1.65 1.96 Node240']]"; //przejazd dwoch robotow
		//return "[['goto 2.5 4.08 Node172', 'search Space171', 'goto 2.10046423835 4.27981430466 Node173', 'goto 1.89958397485 4.2797226499 Node163', 'goto 1.7 4.08 Node162', 'goto 1.50041602515 3.8802773501 Node164', 'goto 1.29951923803 3.88013736056 Node209', 'goto 0.7 4.08 Node207', 'search Space206', 'goto 0.550089970495 3.3604918387 Node208', 'goto 0.550427231222 3.15974024342 Node222', 'goto 0.61041489197 2.60027904239 Node224', 'goto 0.609870631957 2.39951702597 Node186', 'goto 0.46 1.94 Node183'],['goto 0.37 0.69 Node234', 'goto 0.63 0.94 Node236', 'goto 0.86 0.94 Node237', 'goto 1.56 0.9 Node238', 'goto 1.65 1.31 Node240', 'goto 1.65 1.49 Node239', 'goto 1.62 1.97 Node239']]"; //przejazd dwoch robotow	}	
		//return "[['goto 2.5 4.08 Node172', 'search Space171', 'goto 2.10046423835 4.27981430466 Node173', 'goto 1.89958397485 4.2797226499 Node163', 'goto 1.7 4.08 Node162', 'goto 1.50041602515 3.8802773501 Node164', 'goto 1.29951923803 3.88013736056 Node209', 'goto 0.7 4.08 Node207', 'search Space206', 'goto 0.550089970495 3.3604918387 Node208', 'goto 0.550427231222 3.15974024342 Node222', 'goto 0.61041489197 2.60027904239 Node224', 'goto 0.609870631957 2.39951702597 Node186', 'goto 0.46 1.94 Node183'],['goto 0.46 1.94 Node183', 'goto 0.275156841626 1.48047476384 Node185', 'goto 0.275068197237 1.2795046727 Node235', 'goto 0.37 0.69 Node234', 'goto 0.63 0.94 Node236', 'goto 0.86 0.94 Node237', 'goto 1.56 0.9 Node238', 'goto 1.65 1.3 Node239', 'goto 1.65 1.49 Node239', 'goto 1.65 1.96 Node240']]"; //przejazd dwoch robotow	}
		
		//return "[['goto 2.5 4.08 Node172', 'search Space171', 'goto 2.10046423835 4.27981430466 Node173', 'goto 1.89958397485 4.2797226499 Node163', 'goto 1.7 4.08 Node162', 'goto 1.50041602515 3.8802773501 Node164', 'goto 1.29951923803 3.88013736056 Node209', 'goto 0.7 4.08 Node207', 'search Space206', 'goto 0.550089970495 3.3604918387 Node208', 'goto 0.550427231222 3.15974024342 Node222', 'goto 0.61041489197 2.60027904239 Node224'],['goto 0.46 1.94 Node183', 'goto 0.275156841626 1.48047476384 Node185', 'goto 0.275068197237 1.2795046727 Node235', 'goto 0.37 0.69 Node234', 'goto 0.63 0.94 Node236', 'goto 0.86 0.94 Node237', 'goto 1.56 0.9 Node238', 'goto 1.65 1.3 Node239', 'goto 1.65 1.49 Node239', 'goto 1.65 1.96 Node240']]"; //przejazd dwoch robotow	 --ok plan 


	}
	
	private String getJSONPlan(String sJSONResponse) 
	{
		//przetworzenie odpowiedzi na plan
		return "";
	}

	private String getRequest(String sJSON)
	{
		//przygotowanie JSON-a do paczki przed wyslaniem.

		return "";
	}

	private String getResponse(String sRequest) throws ClientProtocolException, IOException 
	{
		String buffor = new String();    
		StringEntity input = new StringEntity(sRequest);

		post.setEntity(input);
		HttpResponse response = client.execute(post);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";

		while ((line = rd.readLine()) != null) 
			buffor += line;

		rd.close();

		return buffor;
	}

	public String Test(String sRequest) throws ClientProtocolException, IOException
	{
		return getResponse(sRequest);	
	}

	//{		    
	//StringEntity input = new StringEntity("product");
	//post.setEntity(input);

	//		// HttpClient client = new DefaultHttpClient();
	//	        HttpGet request = new HttpGet("http://restUrl");
	//	        HttpResponse response = client.execute(request);
	//	        
	//	        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
	//	        String line = "";
	//	        while ((line = rd.readLine()) != null) {
	//	          System.out.println(line);

	/*String chunk;


		HttpResponse httpResponse = httpClient.execute(httpGetRequest); // Execute HTTP request

		System.out.println("----------------------------------------");
		System.out.println(httpResponse.getStatusLine());
		System.out.println("----------------------------------------");

		HttpEntity entity = httpResponse.getEntity();

		byte[] buffer = new byte[1024];
		if (entity != null) 
		{
			InputStream inputStream = entity.getContent();
			int bytesRead = 0;
			BufferedInputStream bis = new BufferedInputStream(inputStream);

			while ((bytesRead = bis.read(buffer)) != -1) 
			{
				chunk = new String(buffer, 0, bytesRead);
				return chunk;
			}
		}
		return null;*/
	//	}	      


	//		      System.out.println("----------------------------------------");
	//		      System.out.println(httpResponse.getStatusLine());
	//		      System.out.println("----------------------------------------");
	//		
	//		      


	// If the response does not enclose an entity, there is no need
	// to bother about connection release

	//  }


	//	}


	/* 
	 HttpClient httpClient = new DefaultHttpClient();

	 //try {
	      // this twitter call returns json results.
	      // see this page for more info: <a href="https://dev.twitter.com/docs/using-search" title="https://dev.twitter.com/docs/using-search">https://dev.twitter.com/docs/using-search</a>
	      // <a href="http://search.twitter.com/search.json?q=%40apple" title="http://search.twitter.com/search.json?q=%40apple">http://search.twitter.com/search.json?q=%40apple</a>

	      // Example URL 1: this yahoo weather call returns results as an rss (xml) feed
	      //HttpGet httpGetRequest = new HttpGet("http://weather.yahooapis.com/forecastrss?p=80020&u=f");

	      // Example URL 2: this twitter api call returns results in a JSON format

	      HttpGet httpGetRequest = new HttpGet("http://search.twitter.com/search.json?q=%40apple");

	      // Execute HTTP request
	      HttpResponse httpResponse = httpClient.execute(httpGetRequest);

	      System.out.println("----------------------------------------");
	      System.out.println(httpResponse.getStatusLine());
	      System.out.println("----------------------------------------");


	      HttpEntity entity = httpResponse.getEntity();

	      // If the response does not enclose an entity, there is no need
	      // to bother about connection release
	      byte[] buffer = new byte[1024];
	      if (entity != null) 
	      {
	        InputStream inputStream = entity.getContent();
	        //try 
	        {
	          int bytesRead = 0;
	          BufferedInputStream bis = new BufferedInputStream(inputStream);
	          while ((bytesRead = bis.read(buffer)) != -1) 
	          {
	            String chunk = new String(buffer, 0, bytesRead);
	            System.out.println(chunk);
	          }
	        }
	      }
	 */

}
