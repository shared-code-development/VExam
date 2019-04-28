import {getRequest} from './api'
import {Message} from 'element-ui'

export const isNotNullORBlank = (...args)=> {
  for (var i = 0; i < args.length; i++) {
    var argument = args[i];
    if (argument == null || argument == '' || argument == undefined) {
      Message.warning({message: '数据不能为空!'})
      return false;
    }
  }
  return true;
}
export const initMenu = (router, store)=> {
  if (store.state.routes.length > 0) {
    return;
  }
  getRequest("/system/config/menu/tree?parentId=0").then(resp=> {
    if (resp && resp.status == 200) {
      let fmtRoutes = formatRoutes(resp.data.obj);
      router.addRoutes(fmtRoutes);
      store.commit('initMenu', fmtRoutes);
      store.dispatch('connect');
    }
  })
}
export const formatRoutes = (routes)=> {
  let fmRoutes = [];
  routes.forEach(router=> {
    let {
      path,
      component,
      name,
      iconCls,
      children
    } = router;
    if (children && children instanceof Array) {
      children = formatRoutes(children);
    }
    let fmRouter = {
      path: path,
      name: name,
      iconCls: iconCls,
      children: children,
      component(resolve){
        if (component.startsWith("Home")) {
          require(['../components/' + component + '.vue'], resolve)
        } else if (component.startsWith("System")) {
          require(['../components/system/' + component + '.vue'], resolve)
        } else if (component.startsWith("Course")) {
          require(['../components/course/' + component + '.vue'], resolve)
        } else if (component.startsWith("Question")) {
          require(['../components/question/' + component + '.vue'], resolve)
        }
      }
    };
    fmRoutes.push(fmRouter);
  })
  return fmRoutes;
}
