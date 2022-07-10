package org.richard.thymeleaf.tamingthymeleaf.pagination

interface Pageable {
    fun getNumber(): Int

    fun getSize(): Int

    /**
     * static <T> Page<T>	empty()
    Creates a new empty Page.
    static <T> Page<T>	empty(Pageable pageable)
    Creates a new empty Page for the given Pageable.
    long	getTotalElements()
    Returns the total amount of elements.
    int	getTotalPages()
    Returns the number of total pages.
    <U> Page<U>	map(Function<? super T,? extends U> converter)
    Returns a new Page with the content of the current one mapped by the given Function.
     */
    /*
    Sort.Direction
Enumeration for sort directions.
static class 	Sort.NullHandling
Enumeration for null handling hints that can be used in Sort.Order expressions.
static class 	Sort.Order
PropertyPath implements the pairing of an Sort.Direction and a property.
static class 	Sort.TypedSort<T>
Extension of Sort to use method handles to define properties to sort by.
     */

    /*
    Pageable	first()
Returns the Pageable requesting the first page.
long	getOffset()
Returns the offset to be taken according to the underlying page and page size.
int	getPageNumber()
Returns the page to be returned.
int	getPageSize()
Returns the number of items to be returned.
Sort	getSort()
Returns the sorting parameters.
default Sort	getSortOr(Sort sort)
Returns the current Sort or the given one if the current one is unsorted.
boolean	hasPrevious()
Returns whether there's a previous Pageable we can access from the current one.
default boolean	isPaged()
Returns whether the current Pageable contains pagination information.
default boolean	isUnpaged()
Returns whether the current Pageable does not contain pagination information.
Pageable	next()
Returns the Pageable requesting the next Page.
static Pageable	ofSize(int pageSize)
Creates a new Pageable for the first page (page number 0) given pageSize .
Pageable	previousOrFirst()
Returns the previous Pageable or the first Pageable if the current one already is the first one.
default Optional<Pageable>	toOptional()
Returns an Optional so that it can easily be mapped on.
static Pageable	unpaged()
Returns a Pageable instance representing no pagination setup.
Pageable	withPage(int pageNumber)
Creates a new Pageable with pageNumber applied.
     */

}