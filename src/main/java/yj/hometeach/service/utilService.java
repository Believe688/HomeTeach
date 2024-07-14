package yj.hometeach.service;

/**
 * @DATE 2024/6/22
 * @USER Linn
 */
public interface utilService {
    String sendOTP(String phone);
    String generateId(int length);
    String getTime();
}
