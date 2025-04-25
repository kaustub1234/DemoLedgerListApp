data class TransactionItem(val mid: Int, val tid: Long, val amount: Double, val narration: Long)
data class TidGroup(val tid: Long, val items: List<TransactionItem>, var isExpanded: Boolean = false)
data class MidGroup(val mid: Int, val tidGroups: List<TidGroup>, var isExpanded: Boolean = false)