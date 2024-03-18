#ifndef __TWOFISH__H
#define __TWOFISH__H
#include "stdint.h"

#define TWOFISH
#ifdef  TWOFISH
typedef struct twofish_t 
{
    uint8_t len;
    uint32_t k[40];
    uint32_t s[4][256];
}twofish_t;
#endif
/*
 * Twofish MDS Multiply Function
 * 
 * Description:
 *
 * @param   tf_twofish
 * @param   data
 * @param   cypher
 * @usage
 * {@code}
 */
void Twofish_encryt(twofish_t* tf_twofish, uint8_t *data, uint8_t *cypher);
/*
 * Twofish Decryption Function
 * 
 * Description:
 *
 * @param   tf_twofish
 * @param   cypher
 * @param   data
 * @usage
 * {@code}
 */
void Twofish_decryt(twofish_t* tf_twofish, uint8_t *cypher, uint8_t *data);
/*
 * Twofish Setup Function
 * 
 * Description:
 *
 * @param   s
 * @param   len
 * @usage
 * {@code}
 */
twofish_t*  Twofish_setup(uint8_t *s, uint32_t len);

#endif