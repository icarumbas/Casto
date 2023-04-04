package com.icarumbas.casto.interactor

import com.icarumbas.casto.api.icons.CastoIconsApi
import com.icarumbas.casto.storage.icons.IconsStorage

class IconsInteractor(
    private val iconsApi: CastoIconsApi,
    private val iconsStorage: IconsStorage,
) {

    suspend fun getIconPath(symbol: String): String? {
        if (iconsStorage.exists(symbol)) {
            return iconsStorage.getRelativePath(symbol)
        } else {
            val data = iconsApi.getIcon(symbol)
            if (data != null) {
                iconsStorage.save(symbol, data)
                return iconsStorage.getRelativePath(symbol)
            } else {
                return null
            }
        }
    }
}