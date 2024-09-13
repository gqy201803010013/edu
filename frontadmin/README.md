# vue-admin-template

> A minimal vue admin template with Element UI & axios & iconfont & permission control & lint

**Live demo:** http://panjiachen.github.io/vue-admin-template

[中文文档](https://github.com/PanJiaChen/vue-admin-template/blob/master/README-zh.md)

## Build Setup

```bash
# Clone project
git clone https://github.com/PanJiaChen/vue-admin-template.git

# Install dependencies
npm install

# Serve with hot reload at localhost:9528
npm run dev

# Build for production with minification
npm run build

# Build for production and view the bundle analyzer report
npm run build --report
```

## Demo

![demo](https://github.com/PanJiaChen/PanJiaChen.github.io/blob/master/images/demo.gif)

## Extra

If you want router permission && generate menu by user roles , you can use this branch [permission-control](https://github.com/PanJiaChen/vue-admin-template/tree/permission-control)

This project is based on `webpack4` development. If you want to use `webpack3` development, please use this branch [webpack3](https://github.com/PanJiaChen/vue-admin-template/tree/webpack3)

For `typescript` version, you can use [vue-typescript-admin-template](https://github.com/Armour/vue-typescript-admin-template) (Credits: [@Armour](https://github.com/Armour))

## Related Project

[vue-element-admin](https://github.com/PanJiaChen/vue-element-admin)

[electron-vue-admin](https://github.com/PanJiaChen/electron-vue-admin)

[vue-typescript-admin-template](https://github.com/Armour/vue-typescript-admin-template)

### Element-Ui using cdn tutorial

First find `index.html`([root directory](https://github.com/PanJiaChen/vue-admin-template/blob/element-ui-cdn/index.html))

Import css and js of `Element`, and then import vue. Because `Element` is vue-dependent, vue must be import before it.

Then find [webpack.base.conf.js](https://github.com/PanJiaChen/vue-admin-template/blob/element-ui-cdn/build/webpack.base.conf.js)
Add `externals` to make webpack not package vue and element.

```
externals: {
  vue: 'Vue',
  'element-ui':'ELEMENT'
}
```

Finally there is a small detail to pay attention to that if you import vue in global, you don't need to manually `Vue.use(Vuex)`, it will be automatically mounted, see
[issue](https://github.com/vuejs/vuex/issues/731)

And you can use `npm run build --report` to see the effect

Pictured:
![demo](https://panjiachen.github.io/images/element-cdn.png)

**[Detailed code](https://github.com/PanJiaChen/vue-admin-template/commit/746aff560932704ae821f82f10b8b2a9681d5177)**

**[Branch](https://github.com/PanJiaChen/vue-admin-template/tree/element-ui-cdn)**

## Browsers support

Modern browsers and Internet Explorer 10+.

| [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/edge/edge_48x48.png" alt="IE / Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>IE / Edge | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/firefox/firefox_48x48.png" alt="Firefox" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Firefox | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/chrome/chrome_48x48.png" alt="Chrome" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Chrome | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/safari/safari_48x48.png" alt="Safari" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Safari |
| --------- | --------- | --------- | --------- |
| IE10, IE11, Edge| last 2 versions| last 2 versions| last 2 versions

## License

[MIT](https://github.com/PanJiaChen/vue-admin-template/blob/master/LICENSE) license.

Copyright (c) 2017-present PanJiaChen




| 时间                   | 名称                                                           | 状态     | 申请人                         | 发明人                                                                                         |
|:-----------------------|:---------------------------------------------------------------|:---------|:-------------------------------|:-----------------------------------------------------------------------------------------------|
|申请日：2021.08.11<br>申请公布日：2021.10.29 <br>**授权公告日：2024.07.02 <br>专利号：ZL 2021 1 0921116.9** |一种采用非平衡SM2密钥交换算法的安全通信方法|授权 | 太原理工大学|<br>黄鑫; 尹昊天;柴晟;贺达;邢斌;张鑫;张笛;郭慧娟;张杰|
|申请日：2022.07.22<br>申请公布日：2022.11.01<br>**授权公告日：2023.11.14 <br>专利号：ZL 2022 1 0869100.2**|一种基于带外信道的高效认证密钥交换方法| 授权 | 太原理工大学； <br>铁塔能源有限公司山西分公司| <br>黄鑫;尹昊天;邢斌;赵渊;武晓华;彭跃余;赵梁斌;黄佳佳;孙晓欣;李建爽;柴晟;张笛;拉纳·阿布巴卡尔;王唯 |
| 申请日：2022.02.28<br>申请公布日：2022.07.05<br> **授权公告日：2023.07.04 <br>专利 号：ZL 2022 1 0186520.0** |一种充电桩的高效认证交易方法| 授权|太原理工大学; <br>苏州博劳科斯信息技术有限公司|<br>黄鑫; 柴晟;邢斌;张笛;李柱银;郭云意;李俊峰;郭慧娟|
| 申请日：2021.08.11<br>申请公布日：2021.10.22<br>授权公告日：2023.04.07|基于见证人端及共享对称密钥的区块链存证方法及系统| 授权 |苏州同济区块链研究院有限公司; <br>太原理工大学;<br>苏州博劳科斯信息技术有限公司;<br>上海信联信息发展股份有限公司 |叶蔚; 龚生智;马小峰;万强;毛秀泽;周先先;诸旦;黄鑫;刘帆;尹昊天;李柱锟;郭云意;李俊锋;郭慧娟 
|申请日：2021.08.11<br>申请公布日：2021.11.05<br>授权公告日：2023.04.07|基于见证人端及共享公钥的区块链存证方法及装置  |授权 |苏州同济区块链研究院有限公司; <br>太原理工大学;<br>苏州博劳科斯信息技术有限公司;<br>上海信联信息发展股份有限公司 |叶蔚;龚生智;马小峰;万强;毛秀泽;周先先;诸旦;黄鑫;刘帆;尹昊天;孙晓欣;黄佳佳;李建爽;郭慧娟|
|申请日：2021.04.08<br>申请公布日：2021.07.09<br>**授权公告日：2022.08.16<br> 专利号:ZL202110377287.X**| 一种基于物理不可克隆函数算法的存证验证方法|授权|太原理工大学|黄鑫;张杰;张笛  |
| 申请日：2020.12.04<br>申请公布日：2021.04.02<br>**授权公告日：2022.06.21 <br>专利号:ZL 2020 1 1412468.3** |一种蓝牙设备计算量非对称认证方法|授权 |太原理工大学|黄鑫;张杰;张笛|
|申请日：2020.11.18<br>申请公布日：2021.02.02<br>授权公告日：2023.02.28  | 一种双活数据库主备服务器读写方法 |授权|太原理工大学|张杰;黄鑫  |
| 申请号：202211358632.6 |  一种适用于元宇宙电力交易的轻量化认证密钥交换方法（刘超杰论文） | 进入实审|核工业北京化工冶金研究院 苏州博劳克斯信息技术有限公司|发明人：刘啸尘、张鑫，柴晟，赵世杰，孙晓欣，王晨旭、李占岭、干雨杭、王伟、黄鑫、郭慧娟
|申请日：2022.07.22<br>申请公布日：2022.11.25"<br>|一种基于隐私保护的高效认证密钥交换方法| 正在申请 | 太原理工大学；<br>铁塔能源有限公司山西分公司 | 黄鑫; 尹昊天;张鑫;赵渊;武晓华;彭跃余;赵梁斌;孙晓欣;邢斌  |
| 申请日：2022.08.18<br>申请公布日：2022.11.25| 一种基于SM9密钥交换算法的身份认证方法 |正在申请| 太原理工大学；<br>铁塔能源有限公司山西分公司|黄鑫;黄佳佳;邢斌;赵渊;武晓华;彭跃余;赵梁斌;张鑫<br>|
|申请日：2022.06.20<br>申请公布日：2022.09.13|一种基于区块链的数字证据存证和验证方法 | 正在申请|太原理工大学| <br>黄鑫; 尹昊天;胡鹏华;初旭阳;杨明理;张笛;邢斌  |
|申请日：2021.04.08<br>申请公布日：2021.07.06| 一种提供地址信息的区块链存证验证方法| 正在申请| 太原理工大学;| 黄鑫;张杰;张笛 |
|申请日：2022.04.29<br>申请公布日：2022.08.02|基于格的前向安全的密文数据检索方法  |正在申请| 太原理工大学; |蔚晓玲;徐磊;黄鑫;许春根  |
|申请日：2022.01.27<br>申请公布日：2022.05.10| 一种认证密钥交换方法、证据上链存证方法及装置  |正在申请|苏州数桐数字科技有限公司;<br>太原理工大学;<br>苏州博劳科斯信息技术有限公司| 马小峰; 叶蔚;龚生智;黄鑫;尹昊天;张笛;柴晟;郭慧娟;刘帆 |
|申请日：2022.01.27<br>申请公布日：2022.05.10| 一种用于多方存证的一键式密钥建立方法和装置  | 正在申请| 苏州数桐数字科技有限公司;<br>太原理工大学;<br>苏州博劳科斯信息技术有限公司|<br>马小峰;龚生智;叶蔚;黄鑫;张笛;王晨旭;柴晟;尹昊天;郭慧娟;刘帆|
|申请日：2022.01.27<br>申请公布日：2022.05.06|一种会话密钥的协商方法、数据存证方法及装置 | 正在申请| 苏州数桐数字科技有限公司;<br>太原理工大学;<br>苏州博劳科斯信息技术有限公司|<br>马小峰;叶蔚;龚生智;黄鑫;柴晟;尹昊天;张笛;郭慧娟;刘帆 |
|申请日：2021.12.24<br>申请公布日：2022.03.22| 一种实时上传交易信息的区块链存证方法 | 正在申请 | 中远海运科技股份有限公司;<br>苏州数桐数字科技有限公司;<br>太原理工大学;<br>苏州博劳科斯信息技术有限公司  | <br>章扬;叶蔚;龚生智;马小峰;万强;毛秀泽;周先先;黄鑫;尹昊天;柴晟;张笛;郭慧娟|
|申请日：2021.07.30<br>申请公布日：2021.11.16|一种基于区块链的学历、学位认证方法|驳回  | 太原理工大学 |黄鑫;陈梦洁;郭慧娟;柴晟 |
|申请日：2022.02.28 <br>申请公布日：2022.05.13|一种智能电表用电信息安全上载方法| 驳回  |太原理工大学; <br>苏州博劳科斯信息技术有限公司| 黄鑫; 孙晓欣;黄佳佳;李建爽;尹昊天;张鑫;郭慧娟 |       
|  |  核电厂中基于动态 ID 的抗泄漏隐私增强Oracle（吴佳星论文） | 未申请||黄鑫 孙晓欣  张正茁   杨昱坤
|  |  一种结合代理重加密与智能合约的权限分离可搜索加密算法（陈世佳论文）   |未申请||
|  |  一种用于智能电表的轻量级抗泄露身份验证密钥交换协议（程然论文）  |投递中||黄鑫 郭庆昱  黄佳佳   肖为恩
|  |  一种基于同态加密的三因素认证方案（杨昱坤论文）|投递中||黄鑫 杨昱坤 王爱嘉 梁博轩 郭华 段子豪 郭庆昱
|  |  基于PCL的认证协议自动化验证方案（张正茁论文）|投递中||黄鑫 张正茁 吴倩倩 王影 段子豪 雷震 蔚老师