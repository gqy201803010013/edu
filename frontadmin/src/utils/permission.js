import store from '@/store'
import { constantRouter } from '@/router'
/**
 * @param {Array} value
 * @returns {Boolean}
 * @example see @/views/permission/directive.vue
 */
export default function checkPermission(value) {
  if (value && value instanceof Array && value.length > 0) {
    const roles = store.getters && store.getters.roles
    const permissionRoles = value

    const hasPermission = roles.some(role => {
      return permissionRoles.includes(role)
    })

    if (!hasPermission) {
      return false
    }
    return true
  } else {
    console.error(`need roles! Like v-permission="['admin','editor']"`)
    return false
  }
}

// 用来控制按钮的显示
export function hasBtnPermission(permission) {
  const myBtns = store.getters.buttons
  return myBtns.indexOf(permission) > -1
}
