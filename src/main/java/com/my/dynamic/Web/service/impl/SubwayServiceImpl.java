package com.my.dynamic.Web.service.impl;

import com.alibaba.fastjson.JSON;
import com.my.dynamic.Web.dao.CalculateDataDao;
import com.my.dynamic.Web.dao.DynamicDataDao;
import com.my.dynamic.Web.service.SubwayService;
import com.my.dynamic.entity.result.ResultCode;
import com.my.dynamic.entity.subway.*;
import com.my.dynamic.exception.CommonException;
import com.my.dynamic.utils.BeanMapUtils;
import com.my.dynamic.utils.IdWorker;
import com.my.dynamic.utils.TemplateExpUtil;
import com.power.common.util.DirUtil;
import com.power.common.util.FileUtil;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.*;
import java.nio.file.Paths;
import java.util.*;

@Service
public class SubwayServiceImpl<T> implements SubwayService<T> {

    @Autowired
    private DynamicDataDao dynamicDataDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CalculateDataDao calculateDataDao;

    @Value("${SubwayPara.templateVehicle}")
    private String templateVehicle;

    @Value("${SubwayPara.exportVehicle}")
    private String exportVehicle;

    @Value("${SubwayPara.templateControl}")
    private String templateControl;

    @Value("${SubwayPara.exportControl}")
    private String exportControl;

    @Value("${SubwayPara.templateCross}")
    private String templateCross;

    @Value("${SubwayPara.exportCross}")
    private String exportCross;

    @Value("${SubwayPara.templateVertial}")
    private String templateVertial;

    @Value("${SubwayPara.exportVertial}")
    private String exportVertial;

    @Value("${SubwayPara.templateAbsorber}")
    private String templateAbsorber;

    @Value("${SubwayPara.exportAbsorber}")
    private String exportAbsorber;

    @Value("${SubwayPara.templateSuspension}")
    private String templateSuspension;

    @Value("${SubwayPara.exportSuspension}")
    private String exportSuspension;

    @Value("${SubwayPara.templateTypeVehicle}")
    private String templateTypeVehicle;

    @Value("${SubwayPara.exportTypeVehicle}")
    private String exportTypeVehicle;


    @Value("${SubwayPara.templateAdhesionPara}")
    private String templateAdhesionPara;

    @Value("${SubwayPara.exportAdhesionPara}")
    private String exportAdhesionPara;


    @Value("${SubwayTM.TM}")
    public String temPlatePath;


    public String workDir;

    /**
     * 添加输入的参数
     *
     * @param map
     * @param username
     * @throws CommonException
     */
    @Override
    public void savePara(Map<String, Object> map, String username, String message, String projectId) throws CommonException {
        try {

//            DynamicData data = new DynamicData();


            DynamicData data = dynamicDataDao.findByUsernameAndMessageAndProjectId(username, message, projectId);


            if (data == null) {
                //设置id
                data = new DynamicData();
                String id = idWorker.nextId() + "";
                data.setId(id);

                data.setUsername(username);
                data.setMessage(message);
            }
            //设置数据
            String result = JSON.toJSONString(map);
            data.setData(result);
            data.setProjectId(projectId);


            //保存当前的用户输入的数据
            dynamicDataDao.save(data);
        } catch (Exception e) {
            throw new CommonException(ResultCode.FAIL);
        }

    }

    /**
     * 添加cross输入的参数
     *
     * @param objects
     * @param username
     * @param message
     * @throws CommonException
     */
    public void saveCrossPara(List<CurveSec> objects, String username, String message, String projectId) throws CommonException {
        try {

            DynamicData data = dynamicDataDao.findByUsernameAndMessageAndProjectId(username, message, projectId);


            if (data == null) {
                //设置id
                data = new DynamicData();
                String id = idWorker.nextId() + "";
                data.setId(id);

                data.setUsername(username);
                data.setMessage(message);
            }
            //设置数据
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            for (int i = 0; i < objects.size(); i++) {
                String temp=JSON.toJSONString(objects.get(i));
                sb.append(temp);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            data.setData(sb.toString());
            data.setProjectId(projectId);

            //保存当前的用户输入的数据
            dynamicDataDao.save(data);
        } catch (Exception e) {
            throw new CommonException(ResultCode.FAIL);
        }

    }

    /**
     * 添加cross输入的参数
     *
     * @param objects
     * @param username
     * @param message
     * @throws CommonException
     */
    public void saveVerticlePara(ArrayList<Vertical> objects, String username, String message, String projectId) throws CommonException {
        try {


            DynamicData data = dynamicDataDao.findByUsernameAndMessageAndProjectId(username, message, projectId);


            if (data == null) {
                //设置id
                data = new DynamicData();
                String id = idWorker.nextId() + "";
                data.setId(id);

                data.setUsername(username);
                data.setMessage(message);
            }
            //设置数据
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            for (int i = 0; i < objects.size(); i++) {
                String temp=JSON.toJSONString(objects.get(i));
                sb.append(temp);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            data.setData(sb.toString());
            data.setProjectId(projectId);

            //保存当前的用户输入的数据
            dynamicDataDao.save(data);
        } catch (Exception e) {
            throw new CommonException(ResultCode.FAIL);
        }

    }


    /**
     * 从数据库中获取对应用户，对应id的添加数据
     *
     * @param username
     * @param id
     * @return
     */
    @Override
    public String getPara(String username, String id) throws Exception {
        DynamicData data = dynamicDataDao.findByIdAndUsername(id, username);
        Map map = (Map) JSON.parse(data.getData());
        SubwayBody sbData = BeanMapUtils.mapToBean(map, SubwayBody.class);
//        System.out.println(sbData);
        return data.getData();
    }

    /**
     * 添加车体的参数
     *
     * @param map
     * @param message
     */
    @Override
    public void addVehiclePara(Map<String, Object> map, String username, String message, String projectId) throws CommonException {

        this.savePara(map, username, message, projectId);

        try {
            Map<String, Object> model = new HashMap<>();
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleCount((String) map.get("vehicleCount"));
            vehicle.setBogieCount((String) map.get("bogieCount"));
            vehicle.setWheelsetCount((String) map.get("wheelsetCount"));
            vehicle.setRw((String) map.get("rw"));
            vehicle.setMc((String) map.get("mc"));
            vehicle.setMt((String) map.get("mt"));
            vehicle.setMw((String) map.get("mw"));
            vehicle.setIwx((String) map.get("iwx"));
            vehicle.setIwy((String) map.get("iwy"));
            vehicle.setIwz((String) map.get("iwz"));

            vehicle.setItx((String) map.get("itx"));
            vehicle.setIty((String) map.get("ity"));
            vehicle.setItz((String) map.get("itz"));
            vehicle.setIcx((String) map.get("icx"));
            vehicle.setIcy((String) map.get("icy"));
            vehicle.setIcz((String) map.get("icz"));
            vehicle.setMm((String) map.get("mm"));
            vehicle.setJm((String) map.get("jm"));
            vehicle.setMgb((String) map.get("mgb"));
            vehicle.setJgb((String) map.get("jgb"));
            vehicle.setJrot((String) map.get("jrot"));

            vehicle.setCarLength((String) map.get("carLength"));
            vehicle.setLt((String) map.get("lt"));
            vehicle.setLc((String) map.get("lc"));
            vehicle.setDw((String) map.get("dw"));
            vehicle.setDs((String) map.get("ds"));
            vehicle.setHtw((String) map.get("htw"));
            vehicle.setHtbn((String) map.get("htbn"));
            vehicle.setHbt((String) map.get("hbt"));
            vehicle.setHcb((String) map.get("hcb"));
            vehicle.setHcbst((String) map.get("hcbst"));
            vehicle.setHbtst((String) map.get("hbtst"));

            String path = workDir + "\\";
            model.put("body", vehicle);

            //设置vehicle参数
            TemplateExpUtil.exportTxt(path + exportVehicle, templateVehicle, model);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean create(String workNumber) {

        String path = System.getProperty("user.dir") + "\\calculate_" + workNumber;
        File folder = new File(path);
        //判断当前用户的算法文件夹是否存在
        if (!folder.exists()) {
            //不存在
            workDir = path;
            //创建目录
            FileUtil.mkdirs(path);
            //从模板复制算法数据文件
            DirUtil.copy(Paths.get(temPlatePath), Paths.get(path));
            return false;
        } else {
            //如果存在
            workDir = path;
            return true;
        }

    }

    @Override
    public void addControlPara(Map<String, Object> map, String username, String message, String projectId) throws CommonException {
        this.savePara(map, username, message, projectId);

        Map<String, Object> model = new HashMap<>();
        Control control = new Control();
        control.setOt(String.valueOf(map.get("ot")));
        control.setInitialVelocity(String.valueOf(map.get("initialVelocity")));
        control.setBeginDis(String.valueOf(map.get("beginDis")));
        control.setTotalTime(String.valueOf(map.get("totalTime")));
        control.setTotalDis(String.valueOf(map.get("totalDis")));

        String path = workDir + "\\";
        model.put("body", control);

        //设置vehicle参数
        TemplateExpUtil.exportTxt(path + exportControl, templateControl, model);


    }

    @Override
    public void addCrossPara(List<CurveSec> curve, String username, String message, String projectId) throws CommonException {

        this.saveCrossPara(curve, username, message, projectId);

        Map<String, Object> model = new HashMap<>();

        int crossCount = curve.size();

        String path = workDir + "\\";
        model.put("crossCount", crossCount);
        model.put("cross", curve);

        //设置vehicle参数
        TemplateExpUtil.exportTxt(path + exportCross, templateCross, model);


    }

    @Override
    public void addVertialPara(ArrayList<Vertical> verticals, String username, String message, String projectId) throws CommonException {

        this.saveVerticlePara(verticals, username, message, projectId);

        Map<String, Object> model = new HashMap<>();

        int verticalCount = verticals.size();

        String path = workDir + "\\";
        model.put("verticalCount", verticalCount);
        model.put("vertical", verticals);

        //设置vehicle参数
        TemplateExpUtil.exportTxt(path + exportVertial, templateVertial, model);


    }

    @Override
    public void addAbsorberPara(Map<String, Object> map, String username, String message, String projectId) throws CommonException {

        this.savePara(map, username, message, projectId);

        try {
            Map<String, Object> model = new HashMap<>();
            AbsorberPara absorberPara = new AbsorberPara();
            absorberPara.setDamperPz1((String) map.get("damperPz1"));
            absorberPara.setDetaVPz1((String) map.get("detaVPz1"));
            absorberPara.setDamperPz2((String) map.get("damperPz2"));
            absorberPara.setDetaVPz2((String) map.get("detaVPz2"));

            absorberPara.setDamperSz1((String) map.get("damperSz1"));
            absorberPara.setDetaVSz1((String) map.get("detaVSz1"));
            absorberPara.setDamperSz2((String) map.get("damperSz2"));
            absorberPara.setDetaVSz2((String) map.get("detaVSz2"));

            absorberPara.setDamperSy1((String) map.get("damperSy1"));
            absorberPara.setDetaVSy1((String) map.get("detaVSy1"));
            absorberPara.setDamperSy2((String) map.get("damperSy2"));
            absorberPara.setDetaVSy2((String) map.get("detaVSy2"));


            String path = workDir + "\\";
            model.put("body", absorberPara);

            //设置vehicle参数
            TemplateExpUtil.exportTxt(path + exportAbsorber, templateAbsorber, model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addSuspensionPara(Map<String, Object> map, String username, String message, String projectId) throws CommonException {
        this.savePara(map, username, message, projectId);

        try {
            Map<String, Object> model = new HashMap<>();
            SuspensionPara suspensionPara = new SuspensionPara();
            suspensionPara.setKpx((String) map.get("kpx"));
            suspensionPara.setCpx((String) map.get("cpx"));
            suspensionPara.setKpy((String) map.get("kpy"));
            suspensionPara.setCpy((String) map.get("cpy"));
            suspensionPara.setKpz((String) map.get("kpz"));
            suspensionPara.setCpz((String) map.get("cpz"));

            suspensionPara.setKsx((String) map.get("ksx"));
            suspensionPara.setCsx((String) map.get("csx"));
            suspensionPara.setKsy((String) map.get("ksy"));
            suspensionPara.setCsy((String) map.get("csy"));
            suspensionPara.setKsz((String) map.get("ksz"));
            suspensionPara.setCsz((String) map.get("csz"));


            suspensionPara.setKpnx((String) map.get("kpnx"));
            suspensionPara.setCpnx((String) map.get("cpnx"));

            suspensionPara.setDetaSy1((String) map.get("detaSy1"));
            suspensionPara.setKsyS1((String) map.get("ksyS1"));
            suspensionPara.setDetaSy2((String) map.get("detaSy2"));
            suspensionPara.setKsyS2((String) map.get("ksyS2"));


            String path = workDir + "\\";
            model.put("body", suspensionPara);

            //设置vehicle参数
            TemplateExpUtil.exportTxt(path + exportSuspension, templateSuspension, model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addTypeVehiclePara(Map<String, Object> map, String username, String message, String projectId) throws CommonException {
        this.savePara(map, username, message, projectId);

//        this.saveTypeVehiclePara(typeVehicles, username, message,projectId);

//        Map<String, Object> model = new HashMap<>();
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < typeVehicles.length; i++) {
//            sb.append(typeVehicles[i].getType());
//            sb.append(" ");
//        }
//        sb.deleteCharAt(sb.length() - 1);
        try {
            Map<String, Object> model = new HashMap<>();
            TypeVehicle typeVehicle = new TypeVehicle();
            typeVehicle.setType((String) map.get("type"));
            typeVehicle.setType((String) map.get("tractionSecNum"));
            typeVehicle.setType((String) map.get("tractionTimeSec"));
            typeVehicle.setType((String) map.get("tractionTypeSec"));


            String path = workDir + "\\";
            model.put("body", typeVehicle);

            //设置vehicle参数
            TemplateExpUtil.exportTxt(path + exportTypeVehicle, templateTypeVehicle, model);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void saveResult(String username, String projectId) throws Exception {

//        String[] recs={"rec_Fwrn_0","rec_motor_0","rec_vehicle_0","rec_wx_0"};
//        System.out.println("begin save result");
        File dir = new File(workDir);
        String[] files = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("rec");
            }
        });

        for (int i = 0; i < files.length; i++) {
            File resFile = new File(workDir + "//" + files[i]);
            byte[] bytesArrays = new byte[(int) resFile.length()];
            FileInputStream fis = new FileInputStream(resFile);
            fis.read(bytesArrays);
            fis.close();

            CalculateData calculateData = new CalculateData();
            CalculateData data = calculateDataDao.findByProjectIdAndUsernameAndFileName(projectId, username, files[i]);
            if (data == null) {
                calculateData.setFileBytes(bytesArrays);
                calculateData.setId(idWorker.nextId() + "");
                calculateData.setUsername(username);
                calculateData.setFileName(files[i]);
                calculateData.setProjectId(projectId);
            } else {
                calculateData = data;
                calculateData.setFileBytes(bytesArrays);
            }


            calculateDataDao.save(calculateData);

        }

//        System.out.println("save finished");


    }

    @Override
    public List<CalculateData> getResult(String username, String projectId, int counts) {

        List<CalculateData> data = calculateDataDao.getByUsernameAndProjectId(username, projectId);

        //去除rec_motor_0.dat这个文件的传输
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getFileName().equals("rec_motor_0.dat")) {
                data.remove(i);
            }

        }

        for (int i = 0; i < data.size(); i++) {

            int length = data.get(i).getFileBytes().length;
            if (counts != 10) {
                byte[] tempBytes = Arrays.copyOfRange(data.get(i).getFileBytes(), length / 10 * (counts - 1), length / 10 * counts);
                data.get(i).setFileBytes(tempBytes);
            } else {
                byte[] tempBytes = Arrays.copyOfRange(data.get(i).getFileBytes(), length / 10 * (counts - 1), length);
                data.get(i).setFileBytes(tempBytes);
            }

        }

//        List<String> test=new ArrayList<>();
//        for (int i = 0; i < data.size(); i++) {
//            String content=new String(data.get(i).getFileBytes(),0,data.get(i).getFileBytes().length);
//            test.add(content);
//        }

        return data;
    }

    @Override
    public void calculate() throws IOException {

        File dir = new File(workDir);
        String[] files = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("rec");
            }
        });
        if (files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                File file = new File(workDir + "//" + files[i]);
                if (file.exists()) {
                    file.delete();
                }
            }

        }
//        System.out.println("begin calculate");
        String command = "cd /d " + workDir + "&&start metro_VSD.exe";
        Process process = Runtime.getRuntime().exec("cmd.exe /c" + command);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
//      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream());
        String line;
        while ((line = br.readLine()) != null) {
//            System.out.println(line);
        }

        br.close();

//        try {
//            process.waitFor();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //result is calculate?
//        for (int i = 0; i < files.length; i++) {
//
//            FileInputStream fis = new FileInputStream(new File(workDir + "//" + files[i]));
//
//            while (true) {
//                try {
//                    if ((fis.getChannel().size() != 0)) {
//                        fis.close();
//                        break;
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("calculate finished");

    }

    @Override
    public DynamicData getInputPara(String username, String projectId, String message) {

        DynamicData data = dynamicDataDao.findByUsernameAndMessageAndProjectId(username, message, projectId);

        return data;
    }

    @Override
    public void addAdhesionPara(Map<String, Object> map, String username, String message, String projectId) throws CommonException {

        this.savePara(map, username, message, projectId);

        try {
            Map<String, Object> model = new HashMap<>();
            AdhesionPara adhesionPara = new AdhesionPara();
            adhesionPara.setNumFri((String) map.get("numFri"));
            adhesionPara.setTableFri((String) map.get("tableFri"));
            adhesionPara.setKa((String) map.get("ka"));
            adhesionPara.setKs((String) map.get("ks"));
            adhesionPara.setAp((String) map.get("ap"));
            adhesionPara.setBp((String) map.get("bp"));
            adhesionPara.setAdhSecNum((String) map.get("adhSecNum"));
            adhesionPara.setAdhSecArrays((String) map.get("adhSecArrays"));
            adhesionPara.setAdhTableArrays((String) map.get("adhTableArrays"));
            adhesionPara.setStatus((String) map.get("status"));

            String path = workDir + "\\";
            model.put("body", adhesionPara);

            //设置vehicle参数
            TemplateExpUtil.exportTxt(path + exportAdhesionPara, templateAdhesionPara, model);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public Map<String, Object> getAllPara(String username, String projectId) {

        Map<String, Object> map = new HashMap<>();
        //1.获取 vehicle
        //2.获取 control
        //3.获取 cross
        //4.获取 vertical
        //5.获取 absorber
        //6.获取 suspension
        //7.获取 typeVehicle
        //8.获取 adhesionPara
        List<DynamicData> datas = dynamicDataDao.findByUsernameAndProjectId(username, projectId);
        for (DynamicData data : datas) {
            map.put(data.getMessage(), data.getData());
        }
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            entry.setValue(entry.getValue().replace("\"",''));
//        }
        return map;

    }


    /**
     * 保存动车类型参数
     *
     * @param map
     * @param username
     * @param message
     * @throws CommonException
     */
//    public void saveTypeVehiclePara(Map<String, Object> map, String username, String message,String projectId) throws CommonException {
//        try {
//
//            DynamicData data = dynamicDataDao.findByUsernameAndMessageAndProjectId(username, message,projectId);
//
//
//            if (data == null) {
//                //设置id
//                data = new DynamicData();
//                String id = idWorker.nextId() + "";
//                data.setId(id);
//
//                data.setUsername(username);
//                data.setMessage(message);
//            }
//            //设置数据
//            StringBuffer sb = new StringBuffer();
//            sb.append("[\"");
//            for (int i = 0; i < typeVehicles.length; i++) {
//                sb.append(typeVehicles[i].getType());
//                sb.append(" ");
//            }
//            sb.deleteCharAt(sb.length() - 1);
//            sb.append("\"]");
//            data.setData(sb.toString());
//            data.setProjectId(projectId);
//
//            //保存当前的用户输入的数据
//            dynamicDataDao.save(data);
//        } catch (Exception e) {
//            throw new CommonException(ResultCode.FAIL);
//        }
//
//    }

}
