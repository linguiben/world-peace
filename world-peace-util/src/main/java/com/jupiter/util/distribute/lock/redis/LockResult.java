/**
 * @className LockResult
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-04-28 10:39
 */
package com.jupiter.util.distribute.lock.redis;

import lombok.Getter;
import lombok.Setter;
import org.redisson.api.RLock;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-04-28 10:39
 */
@Getter@Setter
public class LockResult {

    private LockResultStatus lockResultStatus;

    private RLock rLock;
}

enum LockResultStatus {
    SUCCESS,
    FAIL,
    TIMEOUT
}
