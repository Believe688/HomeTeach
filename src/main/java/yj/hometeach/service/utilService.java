package yj.hometeach.service;

/**
 * @DATE 2024/6/22
 * @USER Linn
 */
public interface utilService {
    Boolean sendOTP(String phone);
    String generateId(String phone);
    String getTime();
}
