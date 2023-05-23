/**
 * recieves a string date on format "AAAA-MM-DDT18:37:31+00:00" (timestamp) and returns a string date on format DD-MM-AAAA 
 * @param {*} date format "2023-04-16T18:37:31+00:00" (timestamp)
 * @returns date format DD-MM-AAAA 
 */
export function dateFormatterDDMMAAAA(date) {
    const birthdayUniversalFormat = date.split("T")[0];
    let newDate = birthdayUniversalFormat.split("-");
    newDate = `${newDate[2]}-${newDate[1]}-${newDate[0]}`
    return newDate
}
/**
 * recieves a string date on format DD-MM-AAAA and returns a string date on format AAAA-MM-DD 
 * @param {*} date format DD-MM-AAAA
 * @returns date format AAAA-MM-DD 
 */
export function dateFormatterAAAAMMDD(date){
    let newDate = date.trim().split("-");
    newDate = `${newDate[2]}-${newDate[1]}-${newDate[0]}`
    return newDate
}