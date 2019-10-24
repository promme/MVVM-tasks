package bergco.se.mvvm.storage

class LocalCacheRepository<T> {

    private var transferObject: T? = null

    fun get(): T {
        return transferObject
            ?: throw IllegalStateException("Cache object should never be null! Use getOrNull if this is intended behaviour.")
    }

    fun put(data: T) {
        transferObject = data
    }

    fun containsData(): Boolean {
        return transferObject != null
    }

    fun getOrNull(): T? {
        return transferObject
    }

    fun clearObject() {
        transferObject = null
    }
}